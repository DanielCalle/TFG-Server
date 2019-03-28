package es.ucm.fdi.tfg.app.sa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import es.ucm.fdi.tfg.app.entity.Friendship;
import es.ucm.fdi.tfg.app.entity.FriendshipId;
import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.repository.FriendshipRepository;
import es.ucm.fdi.tfg.app.repository.UserRepository;
import es.ucm.fdi.tfg.app.transfer.TFriendship;

public class SAFriendshipImp implements SAFriendship {

	@Autowired
	private FriendshipRepository friendshipRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public TFriendship create(TFriendship tFriendship) {
		// Find users for to see exist
		Optional<UserApp> optRequester = userRepository.findById(tFriendship.getRequesterUuid());
		Optional<UserApp> optFriend = userRepository.findById(tFriendship.getFriendUuid());

		if (optRequester.isPresent() && optFriend.isPresent()) {

			// Find friendship of this users, if it's exist then return bad request
			FriendshipId friendshipId1 = new FriendshipId();
			FriendshipId friendshipId2 = new FriendshipId();
			friendshipId1.setFriend(tFriendship.getRequesterUuid());
			friendshipId1.setRequester(tFriendship.getFriendUuid());
			friendshipId2.setFriend(tFriendship.getFriendUuid());
			friendshipId2.setRequester(tFriendship.getRequesterUuid());
			Optional<Friendship> optFriendship1 = friendshipRepository.findById(friendshipId1);
			Optional<Friendship> optFriendship2 = friendshipRepository.findById(friendshipId2);

			if (!optFriendship1.isPresent() && !optFriendship2.isPresent()) {
				// Create Friendship and save
				Friendship friendship = new Friendship();
				Date date = new Date();
				friendship.setRequester(optRequester.get());
				friendship.setFriend(optFriend.get());
				friendship.setDate(date);
				friendship.setActive(false);

				friendshipRepository.save(friendship);
				return tFriendship;

			}

		}

		return null;

	}

	@Override
	public TFriendship accept(String requesterUuid, String friendUuid) {
		// Find Friendship and if not exist return not found
		FriendshipId friendshipId = new FriendshipId();
		friendshipId.setFriend(friendUuid);
		friendshipId.setRequester(requesterUuid);
		Optional<Friendship> friendship = friendshipRepository.findById(friendshipId);

		if (friendship.isPresent()) {
			// Change active to true and save the friendship
			friendship.get().setActive(true);
			friendshipRepository.save(friendship.get());
			TFriendship tFriendship = new TFriendship();
			tFriendship.setFriendUuid(friendUuid);
			tFriendship.setRequesterUuid(requesterUuid);
			tFriendship.setDate(friendship.get().getDate().toString());
			tFriendship.setActive(friendship.get().isActive());

			return tFriendship;
		}

		return null;
	}

	@Override
	public FriendshipId delete(String uuid1, String uuid2) {
		// Find the friendship
		FriendshipId friendshipId = new FriendshipId();
		friendshipId.setFriend(uuid1);
		friendshipId.setRequester(uuid2);
		Optional<Friendship> friendship = friendshipRepository.findById(friendshipId);

		// if the friendship exist then remove it else return not found
		if (friendship.isPresent()) {
			friendshipRepository.delete(friendship.get());
			return friendshipId;
		}

		friendshipId.setFriend(uuid2);
		friendshipId.setRequester(uuid1);
		friendship = friendshipRepository.findById(friendshipId);

		// if the friendship exist then remove it else return not found
		if (friendship.isPresent()) {
			friendshipRepository.delete(friendship.get());
			return friendshipId;
		}

		return null;
	}

	@Override
	public TFriendship read(String uuid1, String uuid2) {
		// Find the friendship
		FriendshipId friendshipId = new FriendshipId();
		friendshipId.setFriend(uuid1);
		friendshipId.setRequester(uuid2);
		Optional<Friendship> friendship = friendshipRepository.findById(friendshipId);

		// if the friendship exist then remove it else return not found
		if (friendship.isPresent()) {
			TFriendship tFriendship = new TFriendship();
			tFriendship.setFriendUuid(friendship.get().getFriend().getUuid());
			tFriendship.setRequesterUuid(friendship.get().getRequester().getUuid());
			tFriendship.setDate(friendship.get().getDate().toString());
			tFriendship.setActive(friendship.get().isActive());
			return tFriendship;
		}

		friendshipId.setFriend(uuid2);
		friendshipId.setRequester(uuid1);
		friendship = friendshipRepository.findById(friendshipId);

		// if the friendship exist then remove it else return not found
		if (friendship.isPresent()) {
			TFriendship tFriendship = new TFriendship();
			tFriendship.setFriendUuid(friendship.get().getFriend().getUuid());
			tFriendship.setRequesterUuid(friendship.get().getRequester().getUuid());
			tFriendship.setDate(friendship.get().getDate().toString());
			tFriendship.setActive(friendship.get().isActive());
			return tFriendship;
		}

		return null;
	}

	@Override
	public List<TFriendship> readAll() {
		List<TFriendship> listTFriendship = new ArrayList<>();
		Iterable<Friendship> listFriendship = friendshipRepository.findAll();

		for (Friendship friendship : listFriendship) {
			TFriendship tFriendship = new TFriendship();
			tFriendship.setFriendUuid(friendship.getFriend().getUuid());
			tFriendship.setRequesterUuid(friendship.getRequester().getUuid());
			tFriendship.setDate(friendship.getDate().toString());
			tFriendship.setActive(friendship.isActive());
			listTFriendship.add(tFriendship);
		}

		return listTFriendship;
	}

}
