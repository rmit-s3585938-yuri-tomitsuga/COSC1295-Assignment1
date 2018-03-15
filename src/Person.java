import java.util.ArrayList;
import java.util.List;

public abstract class Person <X> {

	private List<X> friends;
	
	public List<X> getFriends() {
		if (friends == null) {
			friends = new ArrayList<X>();
		}
		return friends;
	}

	public void setFriends(List<X> friends) {
		this.friends = friends;
	}

	abstract boolean addFriend(X friend);
	
	abstract boolean deleteFriend(X friend);
	
	abstract boolean findFriend(X friend);
}
