package prestamosdao.control;

public enum EstadoPrestamo {
  SOLICITADO, ACEPTADO, DENEGADO;

  public static EstadoPrestamo parse(String inpuString) {
    for (EstadoPrestamo t : EstadoPrestamo.values()) {
      if (t.name().equalsIgnoreCase(inpuString)) {
        return t;
      }
    }
    return null;
  }
}
