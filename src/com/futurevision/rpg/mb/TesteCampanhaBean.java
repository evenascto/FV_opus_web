package com.futurevision.rpg.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.futurevision.rpg.bo.CampaignBO;
import com.futurevision.rpg.bo.inter.CampaignBOI;
import com.futurevision.rpg.dao.inter.CampaignDAOI;
import com.futurevision.rpg.entity.Campaign;

@ManagedBean
@RequestScoped
public class TesteCampanhaBean {
	@EJB 
	private CampaignBOI campaignBO;
	
	private String descricao;

	public void AgoraVaiBO() throws Exception {
		/* 
		 * quando chamei o BO, o entityManager fica nulo. Parece que naõ está reconhecendo como um Bean, ou a injeção de dependencia
		 * nao funciona nesse caso.
		 * 
		 * chamando pelo DAO, funciona.
		 * ***********************
		 * 
		 * descricao = campaignBO.getAllCampaign().get(0).getDescription();
		 * */
		
		//List<Campaign> campanhas = campaignDAO.listAll();
		List<Campaign> campanhas = campaignBO.getAllCampaign();
		if(campanhas.size() > 0) 
			descricao = campanhas.get(0).getDescription();
		else 
			descricao = "NENHUMA CAMPANHA CADASTRADA";
	}
	/*
	public void AgoraVaiComDAO() throws Exception {
		List<Campaign> campanhas = campaignDAO.listAll();
		if(campanhas.size() > 0) 
			descricao = campaignDAO.listAll().get(0).getDescription();
		else 
			descricao = "NENHUMA CAMPANHA CADASTRADA";
	}
	*/
	
	public String getDescricao() {
		return descricao;
	}

}
