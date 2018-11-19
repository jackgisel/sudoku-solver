package sudoku;

import java.util.*;


public class Grid 
{
	private int[][]						values;
	

	//
	// DON'T CHANGE THIS.
	//
	// Constructs a Grid instance from a string[] as provided by TestGridSupplier.
	// See TestGridSupplier for examples of input.
	// Dots in input strings represent 0s in values[][].
	//
	public Grid(String[] rows)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}
	
	
	//
	// DON'T CHANGE THIS.
	//
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		return s;
	}


	//
	// DON'T CHANGE THIS.
	// Copy ctor. Duplicates its source. You’ll call this 9 times in next9Grids.
	//
	Grid(Grid src)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
			for (int i=0; i<9; i++)
				values[j][i] = src.values[j][i];
	}
	
	
	// Creates 9 grids filling a empty space with a num 1-9
	public ArrayList<Grid> next9Grids()
	{		
		int xOfNextEmptyCell = 0;
		int yOfNextEmptyCell = 0;

		// Find x,y of an empty cell.
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				if (values[i][j] == 0) {
					xOfNextEmptyCell = i;
					yOfNextEmptyCell = j;
					
				}
			}
			
		}

		if (isFull())
			return null;

		ArrayList<Grid> grids = new ArrayList<Grid>();

		// Create 9 new grids as described in the comments above. Add them to grids.
		for (int x = 1; x <= 9; x++) {
			Grid g = new Grid(this);
			g.values[xOfNextEmptyCell][yOfNextEmptyCell] = x;
			grids.add(g);
		}
		return grids;
	}
	
	
	// Returns true if this grid is legal. A grid is legal if no row, column, or
	// 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	public boolean isLegal()
	{
		HashSet<Integer> checkColumns = new HashSet<Integer>(9);
		HashSet<Integer> checkRows = new HashSet<Integer>(9);
		
		// Check every row. If you find an illegal row, return false.
		// Check every column. If you find an illegal column, return false.
		for(int col = 0; col < values.length; col++) {
			for(int row = 0; row < values[col].length; row++) {
				if(values[col][row] != 0) {
					if(checkColumns.contains(values[col][row])) return false;
					checkColumns.add(values[col][row]);
				}
				if(values[row][col] != 0) {
					if(checkRows.contains(values[row][col])) return false;
					checkRows.add(values[row][col]);
				}
			}
			checkColumns.clear();
			checkRows.clear();
		}
		
		HashSet<Integer> checkBlock = new HashSet<Integer>(9);
		// Check every block. If you find an illegal block, return false.
		for(int block = 0; block < 9; block++) {
			for(int col = block / 3 * 3; col < block / 3 * 3 + 3; col++) {
				for(int row = block % 3 * 3; row < block % 3 * 3 + 3; row++) {
					if(values[col][row] != 0) {
						if(checkBlock.contains(values[col][row])) return false;
						checkBlock.add(values[col][row]);
					}
				}
			}
			checkBlock.clear();
		}
		
		// All rows/cols/blocks are legal.
		return true;
	}
	
	// Returns true if every cell has a number
	public boolean isFull()
	{
		for(int row = 0; row < values.length; row++) {
			for(int col = 0; col < values[row].length; col++) {
				if(values[row][col] == 0)
					return false;
			}
		}
		return true;
	}
	

	// Returns true if two grids equal each other
	public boolean equals(Object x)
	{
		Grid that = (Grid)x;
		for(int row = 0; row < values.length; row++) {
			for(int col = 0; col < values[row].length; col++) {
				if(this.values[row][col] != that.values[row][col])
					return false;
			}
		}
		return true;
	}
	
	public static void main(String args[]) {

	}
}
