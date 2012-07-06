package estradasolidaria.ui.client;

import com.google.gwt.user.client.rpc.impl.RemoteServiceProxy;
import com.google.gwt.user.client.rpc.impl.ClientSerializationStreamWriter;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.impl.RequestCallbackAdapter.ResponseReader;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.RpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.core.client.impl.Impl;
import com.google.gwt.user.client.rpc.impl.RpcStatsContext;

public class EstradaSolidariaService_Proxy extends RemoteServiceProxy implements estradasolidaria.ui.client.EstradaSolidariaServiceAsync {
  private static final String REMOTE_SERVICE_INTERFACE_NAME = "estradasolidaria.ui.client.EstradaSolidariaService";
  private static final String SERIALIZATION_POLICY ="64D3201572284EBF87E4FE822AA67D13";
  private static final estradasolidaria.ui.client.EstradaSolidariaService_TypeSerializer SERIALIZER = new estradasolidaria.ui.client.EstradaSolidariaService_TypeSerializer();
  
  public EstradaSolidariaService_Proxy() {
    super(GWT.getModuleBaseURL(),
      "estradaSolidariaService", 
      SERIALIZATION_POLICY, 
      SERIALIZER);
  }
  
  public void abrirSessao(java.lang.String login, java.lang.String senha, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "abrirSessao");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(login);
      streamWriter.writeString(senha);
      helper.finish(callback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void aceitarSolicitacao(java.lang.Integer idSessao, java.lang.Integer idSolicitacao, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "aceitarSolicitacao");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idSolicitacao);
      helper.finish(callback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void aceitarSolicitacaoPontoEncontro(java.lang.Integer idSessao, java.lang.Integer idSolicitacao, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "aceitarSolicitacaoPontoEncontro");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idSolicitacao);
      helper.finish(callback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void cadastrarCarona(java.lang.Integer idSessao, java.lang.String origem, java.lang.String destino, java.lang.String data, java.lang.String hora, java.lang.String vagas, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "cadastrarCarona");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 6);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessao);
      streamWriter.writeString(origem);
      streamWriter.writeString(destino);
      streamWriter.writeString(data);
      streamWriter.writeString(hora);
      streamWriter.writeString(vagas);
      helper.finish(callback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void cadastrarCaronaMunicipal(java.lang.Integer idSessao, java.lang.String origem, java.lang.String destino, java.lang.String cidade, java.lang.String data, java.lang.String hora, java.lang.String vagas, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "cadastrarCaronaMunicipal");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 7);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessao);
      streamWriter.writeString(origem);
      streamWriter.writeString(destino);
      streamWriter.writeString(cidade);
      streamWriter.writeString(data);
      streamWriter.writeString(hora);
      streamWriter.writeString(vagas);
      helper.finish(callback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void cadastrarInteresse(java.lang.Integer idSessao, java.lang.String origem, java.lang.String destino, java.lang.String data, java.lang.String horaInicio, java.lang.String horaFim, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "cadastrarInteresse");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 6);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessao);
      streamWriter.writeString(origem);
      streamWriter.writeString(destino);
      streamWriter.writeString(data);
      streamWriter.writeString(horaInicio);
      streamWriter.writeString(horaFim);
      helper.finish(callback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void cancelarCarona(java.lang.Integer idSessao, java.lang.Integer idCaronaEscolhida, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "cancelarCarona");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idCaronaEscolhida);
      helper.finish(asyncCallback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void criarUsuario(java.lang.String login, java.lang.String senha, java.lang.String nome, java.lang.String endereco, java.lang.String email, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "criarUsuario");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 5);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(login);
      streamWriter.writeString(senha);
      streamWriter.writeString(nome);
      streamWriter.writeString(endereco);
      streamWriter.writeString(email);
      helper.finish(callback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void deletarInteresse(java.lang.Integer idSessao, java.lang.Integer idInteresse, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "deletarInteresse");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idInteresse);
      helper.finish(asyncCallback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void desistirRequisicao(java.lang.Integer idSessao, java.lang.Integer idCarona, java.lang.Integer idSolicitacao, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "desistirRequisicao");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 3);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idCarona);
      streamWriter.writeObject(idSolicitacao);
      helper.finish(callback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void editarEmail(java.lang.Integer idSessaoAberta, java.lang.String novoEmail, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "editarEmail");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessaoAberta);
      streamWriter.writeString(novoEmail);
      helper.finish(asyncCallback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void editarEndereco(java.lang.Integer idSessaoAberta, java.lang.String novoEndereco, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "editarEndereco");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessaoAberta);
      streamWriter.writeString(novoEndereco);
      helper.finish(asyncCallback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void editarLogin(java.lang.Integer idSessaoAberta, java.lang.String novoLogin, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "editarLogin");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessaoAberta);
      streamWriter.writeString(novoLogin);
      helper.finish(asyncCallback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void editarNome(java.lang.Integer idSessaoAberta, java.lang.String novoNome, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "editarNome");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessaoAberta);
      streamWriter.writeString(novoNome);
      helper.finish(asyncCallback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void editarSenha(java.lang.Integer idSessaoAberta, java.lang.String novaSenha, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "editarSenha");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessaoAberta);
      streamWriter.writeString(novaSenha);
      helper.finish(asyncCallback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void encerrarCarona(java.lang.Integer idSessao, java.lang.Integer idCaronaEscolhida, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "encerrarCarona");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idCaronaEscolhida);
      helper.finish(asyncCallback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void encerrarSessao(java.lang.String login, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "encerrarSessao");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(login);
      helper.finish(callback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void encerrarSistema(com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "encerrarSistema");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 0);
      helper.finish(callback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void enviarEmail(java.lang.Integer idSessao, java.lang.String destino, java.lang.String message, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "enviarEmail");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 3);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessao);
      streamWriter.writeString(destino);
      streamWriter.writeString(message);
      helper.finish(callback, ResponseReader.BOOLEAN);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void getCarona(java.lang.Integer idCarona, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "getCarona");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idCarona);
      helper.finish(callback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void getCaronaUsuario(java.lang.Integer idSessao, int indexCarona, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "getCaronaUsuario");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("I");
      streamWriter.writeObject(idSessao);
      streamWriter.writeInt(indexCarona);
      helper.finish(callback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void getCaroneiros(java.lang.Integer idSessaoAberta, java.lang.String idCarona, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "getCaroneiros");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessaoAberta);
      streamWriter.writeString(idCarona);
      helper.finish(asyncCallback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void getInteresses(java.lang.Integer idSessao, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "getInteresses");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      helper.finish(callback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void getPontosEncontro(java.lang.Integer idSessao, java.lang.Integer idCarona, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "getPontosEncontro");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idCarona);
      helper.finish(callback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void getPontosSugeridos(java.lang.Integer idSessao, java.lang.Integer idCarona, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "getPontosSugeridos");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idCarona);
      helper.finish(callback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void getSolicitacoes(java.lang.Integer idSessao, java.lang.Integer idCarona, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "getSolicitacoes");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idCarona);
      helper.finish(asyncCallback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void getSolicitacoesFeitasConfirmadas(java.lang.Integer idSessao, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "getSolicitacoesFeitasConfirmadas");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      helper.finish(callback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void getSolicitacoesFeitasPendentes(java.lang.Integer idSessao, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "getSolicitacoesFeitasPendentes");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      helper.finish(callback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void getTodasCaronasPegas(java.lang.Integer idSessao, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "getTodasCaronasPegas");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      helper.finish(asyncCallback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void getTodasCaronasUsuario(java.lang.Integer idSessao, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "getTodasCaronasUsuario");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      helper.finish(asyncCallback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void getTrajeto(java.lang.Integer idCarona, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "getTrajeto");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idCarona);
      helper.finish(callback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void getUsuario(java.lang.Integer idSessao, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "getUsuario");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      helper.finish(asyncCallback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void getUsuarioNoSistema(java.lang.Integer idUsuario, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "getUsuarioNoSistema");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idUsuario);
      helper.finish(callback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void localizarCarona(java.lang.Integer idSessao, java.lang.String origem, java.lang.String destino, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "localizarCarona");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 3);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessao);
      streamWriter.writeString(origem);
      streamWriter.writeString(destino);
      helper.finish(callback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void localizarCaronaMunicipal(java.lang.Integer idSessao, java.lang.String cidade, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "localizarCaronaMunicipal");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessao);
      streamWriter.writeString(cidade);
      helper.finish(callback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void localizarCaronaMunicipal(java.lang.Integer idSessao, java.lang.String cidade, java.lang.String origem, java.lang.String destino, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "localizarCaronaMunicipal");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 4);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessao);
      streamWriter.writeString(cidade);
      streamWriter.writeString(origem);
      streamWriter.writeString(destino);
      helper.finish(callback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void pesquisaUsuariosNoSistema(java.lang.String nome, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "pesquisaUsuariosNoSistema");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(nome);
      helper.finish(callback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void reiniciarSistema(com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "reiniciarSistema");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 0);
      helper.finish(callback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void rejeitarSolicitacao(java.lang.Integer idSessao, java.lang.Integer idSolicitacao, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "rejeitarSolicitacao");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idSolicitacao);
      helper.finish(callback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void responderSugestaoPontoEncontro(java.lang.Integer idSessao, java.lang.Integer idCarona, java.lang.String idSugestao, java.lang.String pontos, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "responderSugestaoPontoEncontro");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 4);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idCarona);
      streamWriter.writeString(idSugestao);
      streamWriter.writeString(pontos);
      helper.finish(callback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void reviewCarona(java.lang.Integer idSessao, java.lang.Integer idCarona, java.lang.String review, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "reviewCarona");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 3);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idCarona);
      streamWriter.writeString(review);
      helper.finish(callback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void reviewVagaEmCarona(java.lang.Integer idSessao, java.lang.Integer idCarona, java.lang.String loginCaroneiro, java.lang.String review, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "reviewVagaEmCarona");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 4);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idCarona);
      streamWriter.writeString(loginCaroneiro);
      streamWriter.writeString(review);
      helper.finish(callback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void solicitarVaga(java.lang.Integer idSessao, java.lang.Integer idCarona, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "solicitarVaga");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idCarona);
      helper.finish(callback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void solicitarVagaPontoEncontro(java.lang.Integer idSessao, java.lang.Integer idCarona, java.lang.String ponto, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "solicitarVagaPontoEncontro");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 3);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idCarona);
      streamWriter.writeString(ponto);
      helper.finish(callback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void sugerirPontoEncontro(java.lang.Integer idSessao, java.lang.Integer idCarona, java.lang.String pontos, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "sugerirPontoEncontro");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 3);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessao);
      streamWriter.writeObject(idCarona);
      streamWriter.writeString(pontos);
      helper.finish(callback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void verificarMensagensPerfil(java.lang.Integer idSessao, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "verificarMensagensPerfil");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeObject(idSessao);
      helper.finish(callback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void visualizarPerfil(java.lang.Integer idSessao, java.lang.String login, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "visualizarPerfil");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.Integer/3438268394");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeObject(idSessao);
      streamWriter.writeString(login);
      helper.finish(callback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void zerarSistema(com.google.gwt.user.client.rpc.AsyncCallback callback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("EstradaSolidariaService_Proxy", "zerarSistema");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 0);
      helper.finish(callback, ResponseReader.VOID);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  @Override
  public SerializationStreamWriter createStreamWriter() {
    ClientSerializationStreamWriter toReturn =
      (ClientSerializationStreamWriter) super.createStreamWriter();
    if (getRpcToken() != null) {
      toReturn.addFlags(ClientSerializationStreamWriter.FLAG_RPC_TOKEN_INCLUDED);
    }
    return toReturn;
  }
  @Override
  protected void checkRpcTokenType(RpcToken token) {
    if (!(token instanceof com.google.gwt.user.client.rpc.XsrfToken)) {
      throw new RpcTokenException("Invalid RpcToken type: expected 'com.google.gwt.user.client.rpc.XsrfToken' but got '" + token.getClass() + "'");
    }
  }
}
