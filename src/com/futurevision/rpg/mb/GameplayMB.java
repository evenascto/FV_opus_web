package com.futurevision.rpg.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;

import com.futurevision.rpg.bo.inter.CharacterBOI;
import com.futurevision.rpg.bo.inter.GameBOI;
import com.futurevision.rpg.bo.inter.ScenarioBOI;
import com.futurevision.rpg.entity.Character;
import com.futurevision.rpg.entity.Item;
import com.futurevision.rpg.entity.Scenario;
import com.futurevision.rpg.entity.ScenarioSelection;

@ManagedBean
@ViewScoped
public class GameplayMB implements Serializable {

	@EJB
	private CharacterBOI characterBO;

	@EJB
	private ScenarioBOI scenarioBO;

	@EJB
	private GameBOI gameBO;

	private Scenario scenario;
	private Character character;

	private List<String> choices;
	private List<ScenarioSelection> selections;

	@PostConstruct
	public void init() {
		System.out.println("public void init()");
		character = characterBO.generateCharacter();
		// scenario = scenarioBO.getScenarioById(1);
		choices = new ArrayList<String>();

		System.out.println(character);

		// usado para testes
		scenario = new Scenario();
		scenario.setDescription("Descrição do cenário");
		List<ScenarioSelection> sens = new ArrayList<ScenarioSelection>();
		ScenarioSelection ss = new ScenarioSelection();
		ss.setDescription("Opção 1");
		sens.add(ss);
		ss = new ScenarioSelection();
		ss.setDescription("Opção 2");
		sens.add(ss);
		scenario.setScenarioSelections(sens);

	}

	public void selectScenario(ScenarioSelection selection) {
		// verifica se é uma oção de testar a sorte
		if (scenario.getLuckTest()) {
			scenario = gameBO.testLuck(scenario, character);
		}
		// verifica se é uma opção de fuga
		if (scenario.getFlee()
				&& selection.equals(scenario.getScenarioSelections().get(0))) {
			scenario = scenarioBO.flee(scenario, character);
		}
		scenario = scenarioBO.getScenarioByScenarioSelection(selection);
	}

	/**
	 * Responsável por tratar uma escolha feita nas opções de cenário
	 * 
	 * @param choice
	 *            the choice
	 */
	public void makeChoice(String choice) {

	}

	public void useItem(Item item) {

	}

	public void removeItem(Item item) {

	}

	private void useProvision() {

	}

	// Gets e sets

	public List<String> getChoices() {
		return choices;
	}

	public List<ScenarioSelection> getSelections() {
		// verifica se a pagina é de teste de sorte
		if (scenario.getLuckTest()) {
			selections.clear();
			selections.add(scenarioBO.getLuckScenarioSelection());
		} else {
			selections = scenario.getScenarioSelections();
		}

		return selections;
	}

	public void setSelections(List<ScenarioSelection> selections) {
		this.selections = selections;
	}

	public void setChoices(List<String> choices) {
		this.choices = choices;
	}

}
