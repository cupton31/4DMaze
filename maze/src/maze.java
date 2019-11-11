import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.lang.StringBuilder;
import java.io.*;
import java.util.*;

public class maze {
	
	public static void main (String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
			// Random Number Generator (Wink, Wink)
			Random rand = new Random(); 
		
			// Two Windows
			JOptionPane Window1;
			JOptionPane Window2;
			Window1 = new JOptionPane();
			Window2 = new JOptionPane();
			
			// Window1 - User inputs number of bits
			int N=0;
			while (N<1 || N>45) {
				N = Integer.parseInt(Window1.showInputDialog(
						"Please Enter Maximum Dimension Size (1-45):"));
			}
			
			// Construct Dimensions
			int x_max = N-1;
			int y_max = N-1;
			int z_max = N-1;
			int t_max = N-1;
			
			// Construct Maze -- Each cell is an object.
			// Make x_max * y_max * z_max * t_max (N*N*N*N) cell-nodes and initialize each.
			cell[][][][] maze = new cell[N][N][N][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					for (int k=0; k<N; k++) {
						for (int l=0; l<N; l++) {
							maze[i][j][k][l] = new cell(i,j,k,l,x_max,y_max,z_max,t_max);
						}
					}
				}
			}
			
			// Recursive Backtracker
			// Use a stack to remember order.
			Stack stack = new Stack();
			
			// 1. Make the initial cell the current cell and mark it as visited
			cell current_cell = maze[0][0][0][0]; 
			current_cell.set_visited(true, maze, N);
			// Are there unvisited cells?
			boolean there_are_unvisited_cells = false;
			int i,j,k,l;
			for (i=0; i<=x_max; i++) {
				for (j=0; j<=y_max; j++) {
					for (k=0; k<=z_max; k++) {
						for (l=0; l<=t_max; l++) {
							if (maze[i][j][k][l].visited==false) 
								{ there_are_unvisited_cells = true;break;}
						}if (there_are_unvisited_cells) break;
					}if (there_are_unvisited_cells) break;
				}if (there_are_unvisited_cells) break;
			}
			// 2. While there are unvisited cells.
			while (there_are_unvisited_cells) {
				int curr_x = current_cell.get_x_pos();
				int curr_y = current_cell.get_y_pos();
				int curr_z = current_cell.get_z_pos();
				int curr_t = current_cell.get_t_pos();
				int random=0;
				// a. If the current cell has any neighbors which have not been visited	
				if ((current_cell.plus_x_neighbor()==1)
						|| (current_cell.neg_x_neighbor()==1)
						|| (current_cell.plus_y_neighbor()==1)
						|| (current_cell.neg_y_neighbor()==1)
						|| (current_cell.plus_z_neighbor()==1)
						|| (current_cell.neg_z_neighbor()==1)
						|| (current_cell.plus_t_neighbor()==1)
						|| (current_cell.neg_t_neighbor()==1) ){
					// i. Choose randomly one of the unvisited neighbors
					boolean found_an_unvisited_neighbor = false;
					while (found_an_unvisited_neighbor==false) {
						random = rand.nextInt(8);
						if (random==0) if (current_cell.plus_x_neighbor()==1) found_an_unvisited_neighbor = true;
						if (random==1) if (current_cell.neg_x_neighbor()==1) found_an_unvisited_neighbor = true;
						if (random==2) if (current_cell.plus_y_neighbor()==1) found_an_unvisited_neighbor = true;
						if (random==3) if (current_cell.neg_y_neighbor()==1) found_an_unvisited_neighbor = true;
						if (random==4) if (current_cell.plus_z_neighbor()==1) found_an_unvisited_neighbor = true;
						if (random==5) if (current_cell.neg_z_neighbor()==1) found_an_unvisited_neighbor = true;
						if (random==6) if (current_cell.plus_t_neighbor()==1) found_an_unvisited_neighbor = true;
						if (random==7) if (current_cell.neg_t_neighbor()==1) found_an_unvisited_neighbor = true;
					}
					// ii. Push the current cell to the stack.
					stack.push(current_cell);
					// iii. Remove the wall between the current cell and the chosen cell.
					// iv. Make the chosen cell the current cell and mark it as visited
					switch (random) {
					case 0: 
						current_cell.set_wall_plus_x(false); 
						maze[curr_x+1][curr_y][curr_z][curr_t].set_wall_neg_x(false);
						current_cell = maze[curr_x+1][curr_y][curr_z][curr_t];
						current_cell.set_visited(true, maze, N);
						break;
					case 1:
						current_cell.set_wall_neg_x(false);
						maze[curr_x-1][curr_y][curr_z][curr_t].set_wall_plus_x(false);
						current_cell = maze[curr_x-1][curr_y][curr_z][curr_t];
						current_cell.set_visited(true, maze, N);
						break;
					case 2:
						current_cell.set_wall_plus_y(false);
						maze[curr_x][curr_y+1][curr_z][curr_t].set_wall_neg_y(false);
						current_cell = maze[curr_x][curr_y+1][curr_z][curr_t];
						current_cell.set_visited(true, maze, N);
						break;
					case 3:
						current_cell.set_wall_neg_y(false);
						maze[curr_x][curr_y-1][curr_z][curr_t].set_wall_plus_y(false);
						current_cell = maze[curr_x][curr_y-1][curr_z][curr_t];
						current_cell.set_visited(true, maze, N);
						break;
					case 4:
						current_cell.set_wall_plus_z(false);
						maze[curr_x][curr_y][curr_z+1][curr_t].set_wall_neg_z(false);
						current_cell = maze[curr_x][curr_y][curr_z+1][curr_t];
						current_cell.set_visited(true, maze, N);
						break;
					case 5:
						current_cell.set_wall_neg_z(false);
						maze[curr_x][curr_y][curr_z-1][curr_t].set_wall_plus_z(false);
						current_cell = maze[curr_x][curr_y][curr_z-1][curr_t];
						current_cell.set_visited(true, maze, N);
						break;
					case 6:
						current_cell.set_wall_plus_t(false);
						maze[curr_x][curr_y][curr_z][curr_t+1].set_wall_neg_t(false);
						current_cell = maze[curr_x][curr_y][curr_z][curr_t+1];
						current_cell.set_visited(true, maze, N);
						break;
					case 7:
						current_cell.set_wall_neg_t(false);
						maze[curr_x][curr_y][curr_z][curr_t-1].set_wall_plus_t(false);
						current_cell = maze[curr_x][curr_y][curr_z][curr_t-1];
						current_cell.set_visited(true, maze, N);
						break;
					default: break; 
					}
				} // b. else if the stack is not empty
				else if (stack.isEmpty()==false) {
					// i. Pop a cell from the stack.
					// ii. Make it the current cell.
					current_cell = (cell) stack.pop();
				} // c. else 
				else {
					// i. Pick a random[not random, the first] (unvisited) cell, make it the current cell and mark it as visited.
					boolean done = false;
					for (i=0; i<=x_max; i++) {
						for (j=0; j<=y_max; j++) {
							for (k=0; k<=z_max; k++) {
								for (l=0; l<=t_max; l++) {
									if (maze[i][j][k][l].visited()==false) {
										current_cell = maze[i][j][k][l];
										current_cell.set_visited(true, maze, N);
										done = true;
										break;
									}
								}if (done) break;
							}if (done) break;
						}if (done) break;
					}
				}
				// Are there unvisited cells?
				there_are_unvisited_cells = false;
				for (i=0; i<=x_max; i++) {
					for (j=0; j<=y_max; j++) {
						for (k=0; k<=z_max; k++) {
							for (l=0; l<=t_max; l++) {
								if (maze[i][j][k][l].visited==false) 
									{there_are_unvisited_cells = true; break;}
							}if (there_are_unvisited_cells) break;
						}if (there_are_unvisited_cells) break;
					}if (there_are_unvisited_cells) break;
				}
			} // End Recursive Backtracker.
			
			// Print output.
			// Output File output.txt
			PrintWriter writer = new PrintWriter("maze.txt", "UTF-8");
			JTextArea outputTextArea = new JTextArea(10,20);
			JScrollPane scroller = new JScrollPane(outputTextArea);
			for (i=0; i<N; i++) {
				for (j=0; j<N; j++) {
					for (k=0; k<N; k++) {
						for (l=0; l<N; l++) {
							outputTextArea.append("maze["+i+"]["+j+"]["+k+"]["+l+"] = ");
							if (maze[i][j][k][l].has_wall_plus_x()) {outputTextArea.append("1"); writer.print("1");}
							else {outputTextArea.append("0"); writer.print("0");}
							if (maze[i][j][k][l].has_wall_neg_x()) {outputTextArea.append("1"); writer.print("1");}
							else {outputTextArea.append("0"); writer.print("0");}
							if (maze[i][j][k][l].has_wall_plus_y()) {outputTextArea.append("1"); writer.print("1");}
							else {outputTextArea.append("0"); writer.print("0");}
							if (maze[i][j][k][l].has_wall_neg_y()) {outputTextArea.append("1"); writer.print("1");}
							else {outputTextArea.append("0"); writer.print("0");}
							if (maze[i][j][k][l].has_wall_plus_z()) {outputTextArea.append("1"); writer.print("1");}
							else {outputTextArea.append("0"); writer.print("0");}
							if (maze[i][j][k][l].has_wall_neg_z()) {outputTextArea.append("1"); writer.print("1");}
							else {outputTextArea.append("0"); writer.print("0");}
							if (maze[i][j][k][l].has_wall_plus_t()) {outputTextArea.append("1"); writer.print("1");}
							else {outputTextArea.append("0"); writer.print("0");}
							if (maze[i][j][k][l].has_wall_neg_t()) {outputTextArea.append("1\n"); writer.print("1\n");}
							else {outputTextArea.append("0\n"); writer.print("0\n");}
							writer.println("");
						}
					}
				}
			}
			
			// Window2 - Print out string[]. One per line.
			Window2.showMessageDialog(null,scroller,"4D Maze",JOptionPane.INFORMATION_MESSAGE);
			writer.close();
	}
}