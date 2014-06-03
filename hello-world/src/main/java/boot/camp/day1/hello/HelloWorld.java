package boot.camp.day1.hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelloWorld
{

	public static void main(String[] args) throws IOException
	{
		Path patch = Paths.get("src//test//resources//sample.csv");
		BufferedReader BR = Files.newBufferedReader(patch, StandardCharsets.UTF_8);
		
		Parser person = new Parser(BR);
		
	}
}
