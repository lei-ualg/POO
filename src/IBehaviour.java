public interface IBehaviour {

  public IGameObject gameObject();

  public void gameObject(IGameObject go);

  // é chaamado quando o gameObject é adicionado ao jogo
  void onInit();

  //
  void onEnabled();

  void onDisabled();

  void onDestroy();

  //chamado pelo gameEngine para atualizar a posição, rotação e escala
  void onUpdate(double dT, IInputEvent ie);

  //chamado pelo gameEgine para detetar colisões após atualizações
  void onCollision(List<IGameObject> gol);
}
