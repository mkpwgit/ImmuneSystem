package com.github;

/**
 * <p>
 * </p>
 * @author mikalai.kisel@ihg.com
 * @since 23 Jan, 2015
 */
public class Virus
  extends Cell
{

  private int id;

  public Virus(int size)
  {
    super( size );
    this.id = counter;
    createByRule();
  }
}
