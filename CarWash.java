import java.util.Random;
import java.util.Scanner;


public class CarWash {
	int minutes, hours, shiftTime, carsWaiting, nextArrival, nextService, byPassed, customerWait, totalWait, served, serviceTime, completionTime, longestWait, idle;
	Random rand = new Random();
	
	public static void main(String[] args) {
		CarWashGUI ethan = new CarWashGUI();

	}
	
	public CarWash(int hours, int minutes, int carsWaiting) {
		this.hours = hours;
		this.minutes = minutes;
		this.carsWaiting = carsWaiting;
	}

	public String simulate() {
		String stats;
		BoundedQueue carWashLine;
		
		carWashLine = new BoundedQueue(4);
		stats = "";
		shiftTime = hours * 60  +  minutes;
		for(int i = 0; i < carsWaiting; i++) 
			carWashLine.enqueue(0);
		nextArrival = rand.nextInt(6) + 1;
		nextService = 0;
		byPassed = 0;
		served = 0;
		totalWait = 0;
		longestWait = 0;
		idle = 0;
		
		for(int t = 0; t < shiftTime; t++) {
			//System.out.println("Next Arrival: " + nextArrival);
			if(t == nextArrival) {
				stats += "A customer has arrived!\n";
				if(carWashLine.isFull())
					byPassed++;
				else
					carWashLine.enqueue(nextArrival);
				nextArrival = rand.nextInt(6) + 1 + t;
			}
			
			if(t == nextService) {
				if(!carWashLine.isEmpty()) {	
					stats += "A customer has started service.\n";
					served++;
					customerWait = t - (int) carWashLine.dequeue();
					if(customerWait > longestWait)
						longestWait = customerWait;
					totalWait += customerWait;
					serviceTime = rand.nextInt(4) + 2;
					completionTime = t + serviceTime;
					nextService = completionTime;
				} else {
					idle++;
					nextService++;
				}
			}
			//System.out.println(t);
		}
		
		stats += "\nThe total number of cars served was: " + served + "\n";
		stats += "The car wash was idle for " + idle + " minutes.\n";
		stats += "The average wait time was " + totalWait/served + " minutes.\n";
		stats += "The longest wait time was " + longestWait + " minutes.\n";
		stats += "The number of customers bypassed was: " + byPassed + "\n";
		
		return stats;
	}
}
