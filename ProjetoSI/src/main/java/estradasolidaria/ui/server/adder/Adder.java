package estradasolidaria.ui.server.adder;

import estradasolidaria.ui.server.logic.EstradaSolidariaController;

public class Adder {
	private EstradaSolidariaController sistema;
	
	public Adder(EstradaSolidariaController uniqueInstance) {
		this.sistema = uniqueInstance;
	}

	public void addElements() {
		for(int i = 0; i < 5; i++) {
			sistema.criarUsuario("l"+i, "s"+i, "n"+i, "e"+i, "em"+i);
		}
		
		Integer idSessao = sistema.getMapIdSessao().get(0).getIdSessao();
		
		for(int j = 1; j < 11; j++) {
			sistema.cadastrarCarona(idSessao, "o"+j, "d"+j, "12/12/12", "12:12", j);
		}
		
		sistema.criarUsuario("si1", "si1si1", "nn", "ee", "emem");
	}
}
