package tpnote;

import java.util.List;
import java.util.Map;

public class ServeurDiscussion {
	
	private Map<Role, List<Utilisateur>> mapping_role_utilisateurs;
	private List<Canal> canaux;
	private String nom;
	private Map<Role, List<Habilitation>> mapping_role_habilitations;

}
