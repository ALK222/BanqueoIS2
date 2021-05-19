package tarjetasdao.control;

public enum Tipo {

  DEBITO("debito"), CREDITO("credito"), PREPAGO("prepago");

  private String _name;

  Tipo(String name) {
    _name = name;
  }

  public static Tipo parse(String inpuString) {
    for (Tipo t : Tipo.values()) {
      if (t.name().equalsIgnoreCase(inpuString)) {
        return t;
      }
    }
    return null;
  }
}
