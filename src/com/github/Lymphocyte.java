package com.github;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 * @author mikalai.kisel@ihg.com
 * @since 23 Jan, 2015
 */
public class Lymphocyte
  extends Cell
{

  private int id;
  private int counterLife = 40;
  private boolean isNeedToRemove = false;

  public Lymphocyte( int size, boolean random )
  {
    super( size );
    this.id = counter;
    if ( random ) {
      createRandomly();
    } else {
      createByRule();
    }
  }

  public Lymphocyte( int size )
  {
    super( size );
    this.id = counter;
    createByOppositeRule();
  }

  public Lymphocyte( int[] array )
  {
    super( array );
    this.id = counter;
  }

  private Lymphocyte createClone( int numberOfChanges )
  {
    int[] newArray = new int[array.length];
    System.arraycopy( array, 0, newArray, 0, array.length );
    Lymphocyte lymphocyte = new Lymphocyte( newArray );
    double chance = ( numberOfChanges + 0.0 ) / getSize();
    int i = 0;
    while ( numberOfChanges > 0 ) {
      if ( Math.random() < chance ) {
        lymphocyte.array[i] = ( lymphocyte.array[i] + 1 ) % 2;
        numberOfChanges--;
      }
      if ( i >= getSize() - 1 ) {
        i = 0;
      } else {
        i++;
      }
    }

    return lymphocyte;
  }

  public List<Lymphocyte> getClones( int count )
  {
    final List<Lymphocyte> lymphocytesTemp = new ArrayList<Lymphocyte>();
    int division = count / 2;
    for( int i = 0; i < division; i++ ) {
      lymphocytesTemp.add( createClone( 1 ) );
    }

    for( int i = division; i < count; i++ ) {
      lymphocytesTemp.add( createClone( 2 ) );
    }

    return lymphocytesTemp;
  }

  public int countAffinity( Cell cell )
  {
    int affinity = 0;
    for( int i = 0; i < getSize(); i++ ) {
      if ( cell.array[i] == array[i] ) {
        affinity++;
      }
    }

    return affinity;
  }

  public void decreaseCountLife()
  {
    counterLife--;
  }

  public void increaseCountLife()
  {
    counterLife += 20;
  }

  public int getCounterLife()
  {
    return counterLife;
  }

  public boolean isNeedToRemove()
  {
    return isNeedToRemove;
  }

  public void setNeedToRemove( boolean isNeedToRemove )
  {
    this.isNeedToRemove = isNeedToRemove;
  }

  @Override
  public String toString()
  {
    StringBuilder stringBuilder = new StringBuilder();
    for( int i = 0; i < getSize(); i++ ) {
      stringBuilder.append( array[i] ).append( " " );
    }
    return stringBuilder.toString();
  }

  @Override
  public boolean equals( Object o )
  {
    if ( this == o )
      return true;
    if ( o == null || getClass() != o.getClass() )
      return false;

    Lymphocyte that = (Lymphocyte)o;

    if ( id != that.id )
      return false;

    return true;
  }

  @Override
  public int hashCode()
  {
    return id;
  }
}
