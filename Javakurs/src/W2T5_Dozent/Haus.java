package W2T5_Dozent;

public class Haus
{

	private String s = "Haus";
	
	public class Zimmer
	{
		
		private String s = "Zimmer";
		
		public class Schrank
		{
			
			private String s = "Schrank";
			
			
			public void Ausgabe()
			{
				String s = "Ausgabe";
				
				System.out.println(s);          					 // Ausgabe
				System.out.println(this.s); 						 // Schrank
				System.out.println(Schrank.this.s);                  // Schrank
				System.out.println(Zimmer.this.s);                   // Zimmer  
				System.out.println(Haus.this.s);                     // Haus
				
				// Erstellen der eingebetteten privaten Klasse
				Mantel m = new Mantel();
				m.Ausgabe();
				
			}
			

			private class Mantel
			{
				
				private String s = "Mantel";
				
				public void Ausgabe()
				{
					System.out.println(s + " im " + Schrank.this.s);
					System.out.println(Zimmer.this.s);
				}
				
			}
			
		}

	}	

}
