public interface IBehaviour {

  public IGameObject gameObject();

  public void gameObject(IGameObject go);

  // é chaamado quando o gameObject é adicionado ao jogo
  void onInit();

  //
  void onEnabled();

  void onDisabled();

  void onDestroy();

  void onUpdate(double dT, IInputEvent ie);

  void onCollision(List<IGameObject> gol);
}
