package com.futurevision.rpg.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.futurevision.rpg.bo.CampaignBO;
import com.futurevision.rpg.bo.GameBO;
import com.futurevision.rpg.bo.inter.CampaignBOI;
import com.futurevision.rpg.bo.inter.CharacterBOI;
import com.futurevision.rpg.bo.inter.GameBOI;
import com.futurevision.rpg.bo.inter.GameUserBOI;
import com.futurevision.rpg.bo.inter.ItemBOI;
import com.futurevision.rpg.bo.inter.ScenarioBOI;
import com.futurevision.rpg.dao.inter.CampaignDAOI;
import com.futurevision.rpg.entity.Attribute;
import com.futurevision.rpg.entity.Campaign;
import com.futurevision.rpg.entity.Character;
import com.futurevision.rpg.entity.GameUser;
import com.futurevision.rpg.entity.Item;
import com.futurevision.rpg.entity.ResultCombat;
import com.futurevision.rpg.entity.Scenario;

@ManagedBean
@RequestScoped
public class TesteCampanhaBean {
	@EJB 
	private CampaignBOI campaignBO;
	
	@EJB 
	private CharacterBOI charBO;	

	@EJB 
	private GameBOI gameBO;	
	
	@EJB 
	private GameUserBOI gameUserBO;	

	@EJB 
	private ItemBOI itemBO;	

	@EJB 
	private ScenarioBOI scenarioBO;	

	
	private String descricaoRetorno;

	public void AgoraVaiBO() throws Exception {
		//List<Campaign> campanhas = campaignDAO.listAll();
		List<Campaign> campanhas = campaignBO.getAllCampaign();
		if(campanhas.size() > 0) 
			descricaoRetorno = campanhas.get(0).getDescription();
		else 
			descricaoRetorno = "NENHUMA CAMPANHA CADASTRADA";
	}
	
	public void BuscarCharacter() throws Exception {
		Character c = charBO.searchCharacterById(1);
		if(c != null) 
			descricaoRetorno = c.getName();
		else 
			descricaoRetorno = "NENHUM CHARACTER COM CODIGO 1 ENCONTRADO";
	}	

	public void BuscarGameCombate() throws Exception {
		Character char1 = new Character();
		Character char2 = new Character();
		
		Attribute a1 = new Attribute();
		a1.setValue(90);
		
		Attribute a2 = new Attribute();
		a2.setValue(30);

		char1.setDexterity(a1);
		char2.setDexterity(a2);
		
		Attribute life = new Attribute();
		life.setValue(100);
		char1.setLife(life);
		char2.setLife(life);
		
		ResultCombat combate = gameBO.combat(char1, char2);
		if(combate != null) 
			descricaoRetorno = "vencedor: " + combate.getCharWinner().getName();
		else 
			descricaoRetorno = "ResultCombat não ENCONTRADO";
	}	
	
	public void BuscarGameUser() throws Exception {
		GameUser user = gameUserBO.searchGameUserById(1);
		if(user != null) 
			descricaoRetorno = user.getName();
		else 
			descricaoRetorno = "NENHUM GAMEUSER COM CODIGO 1 ENCONTRADO";
	}
	
	public void BuscarItem() throws Exception {
		Item item = itemBO.searchItemById(1);
		if(item != null) 
			descricaoRetorno = item.getName();
		else 
			descricaoRetorno = "NENHUM ITEM COM CODIGO 1 ENCONTRADO";
	}	
	
	public void BuscarCenario() throws Exception {
		Scenario scenario = scenarioBO.getScenarioById(1);
		if(scenario != null) 
			descricaoRetorno = scenario.getDescription();
		else 
			descricaoRetorno = "NENHUM CENARIO COM CODIGO 1 ENCONTRADO";
	}	

	
	public String getDescricaoRetorno() {
		return descricaoRetorno;
	}

}
