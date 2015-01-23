package com.github;

public class Main
{

  public static void main( String[] args )
  {
    ImmuneSystem immuneSystem = new ImmuneSystem();
    immuneSystem.createSystem();
    immuneSystem.print();
    immuneSystem.learning();
    immuneSystem.print();
    immuneSystem.processCell( new Antigen( Cell.DEFAULT_SIZE ) );

    for (int i=0; i<100; i++) {
      immuneSystem.processCell( new Antigen( Cell.DEFAULT_SIZE ) );
      immuneSystem.processCell( new Lymphocyte( Cell.DEFAULT_SIZE ) );
    }

    System.out.println("Finish!");



  }
}
