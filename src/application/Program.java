package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Sales;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter full file path:");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List <Sales> list = new ArrayList<>();
			String lines = br.readLine();
			while (lines != null) {
				String[] fields = lines.split(",");
				
				list.add(new Sales(Integer.parseInt(fields[0]),Integer.parseInt(fields[1]),fields[2],Integer.parseInt(fields[3]),Double.parseDouble(fields[4])));
	
				lines = br.readLine();
			}
            
			int year = 2016;
			
			Comparator <Sales> comp = (Sales s1, Sales s2) -> s1.averagePrice().compareTo(s2.averagePrice());
			
		 List<Sales> sales = list.stream()
				 .filter(x -> x.getYear() == year)
				 .sorted(comp.reversed())
				 .limit(5)
				 .collect(Collectors.toList());
			        
         System.out.println("Cinco primeiras vendas de 2016 de maior preço médio");  
       
         /*for(Sales sal:sales) {
        	 System.out.println(sal);	 
         }*/  //metodo "for"
         
         sales.forEach(System.out::println);
         
 
		String seller= "Logan";
		
		double sellerTotalValue = list.stream()
				.filter(x -> x.getSeller().toUpperCase().contains(seller.toUpperCase()))
				.filter(x -> x.getMonth() == 1 || x.getMonth() == 7)
				.map(x -> x.getTotal())
				.reduce(0.0, (x, y) -> x + y);
		
		System.out.println ();
		System.out.println("Valor total vendido pelo vendedor Logan nos meses 1 e 7 =  " + String.format("%.2f", sellerTotalValue) );
	
		} catch (IOException e) {

			System.out.println("Error: " + e.getMessage());
		}

		finally {
			sc.close();
		}
	}

}
