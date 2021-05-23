package dominio;

/**
 * Objeto TipoTarjeta, da informacion sobre el tipo de tarjeta bancaria
 */
public enum TipoTarjeta {

  DEBITO, CREDITO, PREPAGO;

  public static TipoTarjeta parse(String inpuString) {
    for (TipoTarjeta t : TipoTarjeta.values()) {
      if (t.name().equalsIgnoreCase(inpuString)) {
        return t;
      }
    }
    return null;
  }
}
