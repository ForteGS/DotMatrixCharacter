import java.util.Scanner;

public class DisplayEditor {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
			System.out.println("enter command (? for help)>");
			String cmd = in.nextLine().trim();
			String parameter = cmd.substring(1); 
			
			switch (cmd.charAt(0)) {
			case '?':	{
				System.out.println("s (save)    l (load)       d (display)");
				System.out.println("n (next)    p (previous)   j (jump)");
				System.out.println("x (delete)  a (add after)  "
						+ "i (insert before)");
				System.out.println("c (context) r (replace)    q (quit)");
				break;
			}
			case 's':	{
				break;
			}
			case 'l':	{
				break;
			}
			case 'd':	{
				break;
			}
			case 'n':	{
				break;
			}
			case 'p':	{
				break;
			}
			case 'j':	{
				break;
			}
			case 'x':	{
				break;
			}
			case 'a':	{
				break;
			}
			case 'i':	{
				break;
			}
			case 'c':	{
				break;
			}
			case 'r':	{
				break;
			}
			case 'q':
				exit = true;
				in.close();
				break;
			}
		}
	}
}
