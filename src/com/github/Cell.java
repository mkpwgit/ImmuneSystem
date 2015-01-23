package com.github;

/**
 * <p>
 * </p>
 * @author mikalai.kisel@ihg.com
 * @since 23 Jan, 2015
 */
public class Cell
{
  public static int counter = 0;
  public static int DEFAULT_SIZE = 30;

  public static int FIRST_PART = 1;
  public static int SECOND_PART = 1;
  public static double FIRST_PART_PERCENT = 0.2;
  public static double SECOND_PART_PERCENT = 0.8;


  private int size = DEFAULT_SIZE;
  protected int array[];

  public Cell( int size )
  {
    counter++;
    this.size = size;
    this.array = new int[size];
  }

  public Cell( int[] array )
  {
    this.array = array;
  }

  public int getSize()
  {
    return size;
  }

  public int[] getArray()
  {
    return array;
  }

  protected void createByRule()
  {
    int vectorDivision = getSize() / ( FIRST_PART + SECOND_PART );
    for( int i = 0; i < vectorDivision; i++ ) {
      fillArray( i, FIRST_PART_PERCENT );
    }

    for( int i = vectorDivision; i < getSize(); i++ ) {
      fillArray( i, SECOND_PART_PERCENT );
    }
  }

  protected void createByOppositeRule()
  {
    int vectorDivision = getSize() / ( FIRST_PART + SECOND_PART );
    for( int i = 0; i < vectorDivision; i++ ) {
      fillArray( i, 1 - FIRST_PART_PERCENT );
    }

    for( int i = vectorDivision; i < getSize(); i++ ) {
      fillArray( i, 1 - SECOND_PART_PERCENT );
    }
  }

  protected void createRandomly()
  {
    for( int i = 0; i < getSize(); i++ ) {
      if ( Math.random() < 0.5 ) {
        array[i] = 0;
      } else {
        array[i] = 1;
      }
    }

  }

  protected void fillArray( int number, double percent )
  {
    if ( Math.random() < percent ) {
      array[number] = 1;
    } else {
      array[number] = 0;
    }
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
}
