package com.github;

/**
 * <p>
 * </p>
 * @author mikalai.kisel@ihg.com
 * @since 23 Jan, 2015
 */
public class Antigen
  extends Cell
{

  private int id;

  public Antigen( int size )
  {
    super( size );
    this.id = counter;
    createByRule();
  }
}
