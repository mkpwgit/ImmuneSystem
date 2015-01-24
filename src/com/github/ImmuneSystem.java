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
  private List<Virus> viruses = new ArrayList<Virus>();

  public void createSystem()
  {
    for( int i = 0; i < immuneFirstSize; i++ ) {
      lymphocytes.add( new Lymphocyte( cellSize, true ) );
    }
  }

  public void learning()
  {
    for( int i = 0; i < trainingSize; i++ ) {
      viruses.add( new Virus( cellSize ) );
    }
    Map<Lymphocyte, Integer> tempLymphsMap = new HashMap<Lymphocyte, Integer>();

    for( Lymphocyte lymphocyte : lymphocytes ) {
      for( Virus virus : viruses ) {
        Integer currAffinity = lymphocyte.countAffinity( virus );
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
    System.out.println( "Processing            : " + cell );
    List<CellAffinity> cellAffinities = new ArrayList<CellAffinity>();
    for( Lymphocyte lymphocyte : lymphocytes ) {
      lymphocyte.decreaseCountLife();
      cellAffinities.add( new CellAffinity(
        lymphocyte, lymphocyte.countAffinity( cell ) ) );
    }

    Collections.sort( cellAffinities );

    Lymphocyte champion = cellAffinities.get( 0 ).getLymphocyte();
    Integer championAffinity = cellAffinities.get( 0 ).getAffintiy();

    if ( championAffinity < champion.getSize() * 0.67 ) {
      System.out.println( "It is a native cell. ******************* " );
    } else {
      champion.increaseCountLife();
      System.out.println( "It is a virus." );
      System.out.println( "Appropriate Lymphocyte: " + champion );
      System.out.println( "Affinity:   " + championAffinity );
      if ( cell instanceof Virus ) {
        viruses.add( (Virus)cell );
      }

      List<Lymphocyte> clones = champion.getClones( 12 );
      CellAffinity cloneChampion = findChampion( clones, cell );
      System.out.println( "Best clone            : " + cloneChampion.getLymphocyte() );
      System.out.println( "Best Clone affinity   : " + cloneChampion.getAffintiy() );

      CellAffinity secondCloneChampion = findSecondChampion( clones, cell );

      System.out.println( "Second clone          : " + secondCloneChampion.getLymphocyte() );
      System.out.println( "Second clone affinity : "
        + secondCloneChampion.getAffintiy() );

      if ( cloneChampion.getAffintiy() > championAffinity ) {
        lymphocytes.add( cloneChampion.getLymphocyte() );
      }

      if ( secondCloneChampion.getAffintiy() > championAffinity ) {
        lymphocytes.add( secondCloneChampion.getLymphocyte() );
      }

    }

    checkLifeCounter();
  }

  public void checkLifeCounter()
  {
    for( Iterator<Lymphocyte> iter = lymphocytes.listIterator(); iter.hasNext(); ) {
      Lymphocyte lymphocyte = iter.next();
      if ( lymphocyte.getCounterLife() <= 0 ) {
        iter.remove();
      }
    }
    System.out.println();
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

  private CellAffinity findSecondChampion( List<Lymphocyte> tempLymphocytes,
    Cell cell )
  {
    List<CellAffinity> cellAffinities = new ArrayList<CellAffinity>();
    for( Lymphocyte lymphocyte : tempLymphocytes ) {
      cellAffinities.add( new CellAffinity(
        lymphocyte, lymphocyte.countAffinity( cell ) ) );
    }

    Collections.sort( cellAffinities );

    return cellAffinities.get( 1 );
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
