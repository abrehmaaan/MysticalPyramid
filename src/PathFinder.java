import java.io.FileNotFoundException;
import java.io.IOException;

public class PathFinder {
	private Map pyramidMap;
	public PathFinder(String fileName) throws InvalidMapCharacterException, FileNotFoundException, IOException {
		pyramidMap = new Map(fileName);
	}
	public DLStack path() {
		DLStack<Chamber> s = new DLStack<Chamber>();
		Chamber c = pyramidMap.getEntrance();
		int N = pyramidMap.getNumTreasures();
		c.markPushed();
		s.push(c);
		int found = 0;
		while(!s.isEmpty()) {
			Chamber top = s.peek();
			if(top.isTreasure()) {
				found++;
			}
			if(top.isTreasure()&&found==N) {
				break;
			}
			Chamber best = bestChamber(top);
			if(best!=null) {
				best.markPushed();
				s.push(best);
			}
			else {
				Chamber ch = s.pop();
				ch.markPopped();
			}
		}
		return s;
	}
	public Map getMap() {
		return pyramidMap;
	}
	public boolean isDim(Chamber currentChamber) {
		if(currentChamber != null && !currentChamber.isSealed() && !currentChamber.isLighted()) {
			for(int i = 0;i<=5;i++) {
				Chamber c = currentChamber.getNeighbour(i);
				if(c!=null) {
					if(c.isLighted()) {
						return true;
					}
				}
			}
			return false;
		}
		return false;
	}
	public Chamber bestChamber(Chamber currentChamber) {
		for(int i = 0;i<=5;i++) {
			Chamber c = currentChamber.getNeighbour(i);
			if(c!=null) {
				if(!c.isMarked()) {
					if(c.isTreasure()) {
						return c;
					}
				}
			}
		}
		for(int i = 0;i<=5;i++) {
			Chamber c = currentChamber.getNeighbour(i);
			if(c!=null) {
				if(!c.isMarked()) {
					if(c.isLighted()) {
						return c;
					}
				}
			}
		}
		for(int i = 0;i<=5;i++) {
			Chamber c = currentChamber.getNeighbour(i);
			if(c!=null) {
				if(!c.isMarked()) {
					if(isDim(c)) {
						return c;
					}
				}
			}
		}
		return null;
	}
}
