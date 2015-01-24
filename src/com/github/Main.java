package com.github;

public class Main
{

  public static void main( String[] args )
  {
    ImmuneSystem immuneSystem = new ImmuneSystem();
    immuneSystem.createSystem();
    immuneSystem.learning();
    System.out.println( "Current lymphocytes:  " );
    immuneSystem.print();
    immuneSystem.processCell( new Virus( Cell.DEFAULT_SIZE ) );

    for( int i = 0; i < 2000; i++ ) {
      immuneSystem.processCell( new Virus( Cell.DEFAULT_SIZE ) );
      immuneSystem.processCell( new Lymphocyte( Cell.DEFAULT_SIZE ) );
    }

    System.out.println( "Finish!" );


  }
}
