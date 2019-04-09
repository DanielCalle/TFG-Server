package es.ucm.fdi.tfg.app.sa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.tfg.app.entity.Friendship;
import es.ucm.fdi.tfg.app.entity.FriendshipId;
import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.repository.FriendshipRepository;
import es.ucm.fdi.tfg.app.repository.UserRepository;
import es.ucm.fdi.tfg.app.transfer.TFriendship;

@Service
public class SAFriendshipImp implements SAFriendship {

	@Autowired
	private FriendshipRepository friendshipRepository;
	@Autowired
	private UserRepository userRepository;

	private ModelMapper modelMapper = new ModelMapper();

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

				friendship = friendshipRepository.save(friendship);
				return modelMapper.map(friendship, TFriendship.class);

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
			Friendship f = friendshipRepository.save(friendship.get());
			return modelMapper.map(f, TFriendship.class);
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
			return modelMapper.map(friendship.get(), TFriendship.class);
		}

		friendshipId.setFriend(uuid2);
		friendshipId.setRequester(uuid1);
		friendship = friendshipRepository.findById(friendshipId);

		// if the friendship exist then remove it else return not found
		if (friendship.isPresent()) {
			return modelMapper.map(friendship.get(), TFriendship.class);
		}

		return null;
	}

	@Override
	public List<TFriendship> readAll() {
		Iterable<Friendship> listFriendship = friendshipRepository.findAll();

		return StreamSupport.stream(listFriendship.spliterator(), false)
				.map(friendShip -> modelMapper.map(friendShip, TFriendship.class))
				.collect(Collectors.toList());
	}

}
