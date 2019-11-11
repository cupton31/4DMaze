
public class cell {
	byte x_pos;
	byte y_pos;
	byte z_pos;
	byte t_pos;
	
	boolean has_wall_plus_x=true;
	boolean has_wall_neg_x=true;
	boolean has_wall_plus_y=true;
	boolean has_wall_neg_y=true;
	boolean has_wall_plus_z=true;
	boolean has_wall_neg_z=true;
	boolean has_wall_plus_t=true;
	boolean has_wall_neg_t=true;
	
	byte plus_x_neighbor=1;   // 0--none, 1--unvisited, 2--visited
	byte neg_x_neighbor=1;    // 0--none, 1--unvisited, 2--visited
	byte plus_y_neighbor=1;   // 0--none, 1--unvisited, 2--visited
	byte neg_y_neighbor=1;    // 0--none, 1--unvisited, 2--visited
	byte plus_z_neighbor=1;   // 0--none, 1--unvisited, 2--visited
	byte neg_z_neighbor=1;    // 0--none, 1--unvisited, 2--visited
	byte plus_t_neighbor=1;   // 0--none, 1--unvisited, 2--visited
	byte neg_t_neighbor=1;    // 0--none, 1--unvisited, 2--visited
	
	
	boolean visited=false;
	
	// Constructor Methods
	public cell (int x, int y, int z, int t, int x_max, int y_max, int z_max, int t_max) {
		x_pos=(byte) x;
		y_pos= (byte) y;
		z_pos= (byte) z;
		t_pos= (byte) t;
		
		if (x==0) neg_x_neighbor=0;
		if (y==0) neg_y_neighbor=0;
		if (z==0) neg_z_neighbor=0;
		if (t==0) neg_t_neighbor=0;
		if (x==x_max) plus_x_neighbor=0;
		if (y==y_max) plus_y_neighbor=0;
		if (z==z_max) plus_z_neighbor=0;
		if (t==t_max) plus_t_neighbor=0;
		
	}
	
	// Access Methods
	int get_x_pos () {return x_pos;}
	int get_y_pos () {return y_pos;}
	int get_z_pos () {return z_pos;}
	int get_t_pos () {return t_pos;}
	boolean has_wall_plus_x () {return has_wall_plus_x;}
	boolean has_wall_neg_x () {return has_wall_neg_x;}
	boolean has_wall_plus_y () {return has_wall_plus_y;}
	boolean has_wall_neg_y () {return has_wall_neg_y;}
	boolean has_wall_plus_z () {return has_wall_plus_z;}
	boolean has_wall_neg_z () {return has_wall_neg_z;}
	boolean has_wall_plus_t () {return has_wall_plus_t;}
	boolean has_wall_neg_t () {return has_wall_neg_t;}
	boolean visited () {return visited;}
	int plus_x_neighbor () {return plus_x_neighbor;}
	int neg_x_neighbor () {return neg_x_neighbor;}
	int plus_y_neighbor () {return plus_y_neighbor;}
	int neg_y_neighbor () {return neg_y_neighbor;}
	int plus_z_neighbor () {return plus_z_neighbor;}
	int neg_z_neighbor () {return neg_z_neighbor;}
	int plus_t_neighbor () {return plus_t_neighbor;}
	int neg_t_neighbor () {return neg_t_neighbor;}
	
	// Mutator Methods
	void set_x_pos (int x) {x_pos = (byte) x;}
	void set_y_pos (int y) {y_pos = (byte) y;}
	void set_z_pos (int z) {z_pos = (byte) z;}
	void set_t_pos (int t) {t_pos = (byte) t;}
	void set_wall_plus_x (boolean v) {has_wall_plus_x = v;}
	void set_wall_neg_x (boolean v) {has_wall_neg_x = v;}
	void set_wall_plus_y (boolean v) {has_wall_plus_y = v;}
	void set_wall_neg_y (boolean v) {has_wall_neg_y = v;}
	void set_wall_plus_z (boolean v) {has_wall_plus_z = v;}
	void set_wall_neg_z (boolean v) {has_wall_neg_z = v;}
	void set_wall_plus_t (boolean v) {has_wall_plus_t = v;}
	void set_wall_neg_t (boolean v) {has_wall_neg_t = v;}
	void set_plus_x_neighbor (int v) {plus_x_neighbor = (byte) v;}
	void set_neg_x_neighbor (int v) {neg_x_neighbor = (byte) v;}
	void set_plus_y_neighbor (int v) {plus_y_neighbor = (byte) v;}
	void set_neg_y_neighbor (int v) {neg_y_neighbor = (byte) v;}
	void set_plus_z_neighbor (int v) {plus_z_neighbor = (byte) v;}
	void set_neg_z_neighbor (int v) {neg_z_neighbor = (byte) v;}
	void set_plus_t_neighbor (int v) {plus_t_neighbor = (byte) v;}
	void set_neg_t_neighbor (int v) {neg_t_neighbor = (byte) v;}
	
	void set_visited (boolean v, cell[][][][] maze, int N) {
		visited = v;
		if (x_pos!=0) maze[x_pos-1][y_pos][z_pos][t_pos].set_plus_x_neighbor(2);
		if (y_pos!=0) maze[x_pos][y_pos-1][z_pos][t_pos].set_plus_y_neighbor(2);
		if (z_pos!=0) maze[x_pos][y_pos][z_pos-1][t_pos].set_plus_z_neighbor(2);
		if (t_pos!=0) maze[x_pos][y_pos][z_pos][t_pos-1].set_plus_t_neighbor(2);
		if (x_pos!=N-1) maze[x_pos+1][y_pos][z_pos][t_pos].set_neg_x_neighbor(2);
		if (y_pos!=N-1) maze[x_pos][y_pos+1][z_pos][t_pos].set_neg_y_neighbor(2);
		if (z_pos!=N-1) maze[x_pos][y_pos][z_pos+1][t_pos].set_neg_z_neighbor(2);
		if (t_pos!=N-1) maze[x_pos][y_pos][z_pos][t_pos+1].set_neg_t_neighbor(2);
	}
}