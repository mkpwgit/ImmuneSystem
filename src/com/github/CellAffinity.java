package com.github;

/**
 * <p>
 * </p>
 * @author mikalai.kisel@ihg.com
 * @since 23 Jan, 2015
 */
public class CellAffinity
  implements Comparable<CellAffinity>
{

  private Lymphocyte lymphocyte;
  private Integer affintiy;

  public CellAffinity( Lymphocyte lymphocyte, Integer affintiy )
  {
    this.lymphocyte = lymphocyte;
    this.affintiy = affintiy;
  }

  public Lymphocyte getLymphocyte()
  {
    return lymphocyte;
  }

  public void setLymphocyte( Lymphocyte lymphocyte )
  {
    this.lymphocyte = lymphocyte;
  }

  public Integer getAffintiy()
  {
    return affintiy;
  }

  public void setAffintiy( Integer affintiy )
  {
    this.affintiy = affintiy;
  }


  @Override
  public int compareTo( CellAffinity o )
  {
    return o.getAffintiy().compareTo( affintiy );
  }
}
