package dominio;

/**
 * Objeto EstadoPrestamo, te dice el estado administrativo de un prestamo
 */
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
