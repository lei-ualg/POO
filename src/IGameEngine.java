public interface IGameEngine {

  public void addEnabled(IGameObject go);

  public void addDisabled(IGameObject go);

  public void enable(IGameObject go);

  public void disable(IGameObject go);

  public boolean isEnabled(IGameObject go);

  public boolean isDisabled(IGameObject go);

  public List<IGameObject> getEnabled();

  public List<IGameObject> getDisabled();

  //Chamam onDestroy de IBehaviour
  public void destroy(IGameObject go);

  public void destroyAll();

  //onUpdate de behaviour
  public void run();

  public void checkCollisions();
}
