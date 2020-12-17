package tpnote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Canal implements Destinataire, Comparable<Canal> {

	private Map<Role, List<Utilisateur>> mapping_role_utilisateurs;
	private Integer ordre;
	private List<Webhook> webhooks;
	private String nom;
	private Map<Role, List<Habilitation>> mapping_role_habilitations;
	private List<Message> historiques;
	
	public Canal() {
		this.mapping_role_habilitations = new HashMap<Role, List<Habilitation>>();
		this.mapping_role_utilisateurs = new HashMap<Role, List<Utilisateur>>();
		this.historiques = new ArrayList<Message>();
	}

	public Map<Role, List<Utilisateur>> getMapping_role_utilisateurs() {
		return mapping_role_utilisateurs;
	}

	public void setMapping_role_utilisateurs(Map<Role, List<Utilisateur>> mapping_role_utilisateurs) {
		this.mapping_role_utilisateurs = mapping_role_utilisateurs;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public List<Webhook> getWebhooks() {
		return webhooks;
	}

	public void setWebhooks(List<Webhook> webhooks) {
		this.webhooks = webhooks;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Map<Role, List<Habilitation>> getMapping_role_habilitations() {
		return mapping_role_habilitations;
	}

	public void setMapping_role_habilitations(Map<Role, List<Habilitation>> mapping_role_habilitations) {
		this.mapping_role_habilitations = mapping_role_habilitations;
	}

	public List<Message> getHistoriques() {
		return historiques;
	}

	public void setHistoriques(List<Message> historiques) {
		this.historiques = historiques;
	}

	public void ecrireMessage(Utilisateur utilisateur, Message message) throws ActionNonAutoriseeException {

		boolean permissionAccordee = false;
		// recuperation des roles de l'utilisateur
		List<Role> roles = new ArrayList<Role>();
		for (Role r : mapping_role_utilisateurs.keySet()) {
			if (mapping_role_utilisateurs.get(r).contains(utilisateur)) {
				roles.add(r);
			}
		}

		// verification qu'un des roles possede le droit d'ecriture
		for (Role r : roles) {
			if (mapping_role_habilitations.get(r).contains(Habilitation.ECRITURE)) {
				permissionAccordee = true;
				break;
			}
		}

		if (permissionAccordee) {
			historiques.add(message);
		} else {
			throw new ActionNonAutoriseeException("erreur");
		}

	}

	public int compareTo(Canal o) {
		return this.ordre - o.ordre;
	}
}
