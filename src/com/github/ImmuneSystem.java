package com.github;

import com.github.util.MapUtil;

import java.util.*;

/**
 * <p>
 * </p>
 * @author mikalai.kisel@ihg.com
 * @since 23 Jan, 2015
 */
public class ImmuneSystem
{

  public static int immuneFirstSize = 400;
  public static int immunePermanentSize = 30;
  public static int trainingSize = 30;
  public static int cellSize = Cell.DEFAULT_SIZE;

  private List<Lymphocyte> lymphocytes = new ArrayList<Lymphocyte>();
  private List<Antigen> antigens = new ArrayList<Antigen>();

  public void createSystem()
  {
    for( int i = 0; i < immuneFirstSize; i++ ) {
      lymphocytes.add( new Lymphocyte( cellSize, true ) );
    }
  }

  public void learning()
  {
    for( int i = 0; i < trainingSize; i++ ) {
      antigens.add( new Antigen( cellSize ) );
    }
    Map<Lymphocyte, Integer> tempLymphsMap = new HashMap<Lymphocyte, Integer>();

    for( Lymphocyte lymphocyte : lymphocytes ) {
      for( Antigen antigen : antigens ) {
        Integer currAffinity = lymphocyte.countAffinity( antigen );
        Integer totalAffinity = tempLymphsMap.get( lymphocyte );
        if ( totalAffinity == null ) {
          tempLymphsMap.put( lymphocyte, currAffinity );
        } else {
          totalAffinity += currAffinity;
          tempLymphsMap.put( lymphocyte, totalAffinity );
        }
      }
    }

    List<Map.Entry<Lymphocyte, Integer>> sortedTempLymps = MapUtil.sortByValueList( tempLymphsMap );
    List<Lymphocyte> newLymphocytes = new ArrayList<Lymphocyte>();

    int counter = 0;
    for( Map.Entry<Lymphocyte, Integer> entry : sortedTempLymps ) {
      newLymphocytes.add( entry.getKey() );
      counter++;
      if ( counter > immunePermanentSize ) {
        break;
      }
    }

    lymphocytes = newLymphocytes;
  }

  public void processCell( Cell cell )
  {
    List<CellAffinity> cellAffinities = new ArrayList<CellAffinity>();
    for( Lymphocyte lymphocyte : lymphocytes ) {
      cellAffinities.add( new CellAffinity(
        lymphocyte, lymphocyte.countAffinity( cell ) ) );
    }

    Collections.sort( cellAffinities );

    Lymphocyte champion = cellAffinities.get( 0 ).getLymphocyte();
    Integer championAffinity = cellAffinities.get( 0 ).getAffintiy();

    if ( championAffinity < champion.getSize() * 0.67) {
      System.out.println( "It is a good cell: " + cell +" ******************* ");
    } else {
      System.out.println( "Virus:      " + cell );
      System.out.println( "Lymphocyte: " + champion );
      System.out.println( "Affinity:   " + championAffinity );
      antigens.add((Antigen) cell);

      List<Lymphocyte> clones = champion.getClones( 12 );
      CellAffinity cloneChampion = findChampion( clones, cell );
      System.out.println( "Best clone: " + cloneChampion.getLymphocyte() );
      System.out.println( "Clone affinity: " + cloneChampion.getAffintiy() );

      if ( cloneChampion.getAffintiy() > championAffinity ) {
        lymphocytes.add( cloneChampion.getLymphocyte() );
      }

    }
  }


  private CellAffinity findChampion( List<Lymphocyte> tempLymphocytes, Cell cell )
  {
    List<CellAffinity> cellAffinities = new ArrayList<CellAffinity>();
    for( Lymphocyte lymphocyte : tempLymphocytes ) {
      cellAffinities.add( new CellAffinity(
        lymphocyte, lymphocyte.countAffinity( cell ) ) );
    }

    Collections.sort( cellAffinities );

    return cellAffinities.get( 0 );
  }

  public void print()
  {
    System.out.println( "=========================================" );
    for( Lymphocyte lymphocyte : lymphocytes ) {
      System.out.println( lymphocyte );
    }
    System.out.println( "=========================================" );
  }


}
