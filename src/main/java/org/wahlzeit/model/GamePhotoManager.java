package org.wahlzeit.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

import com.google.appengine.api.images.Image;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;

public class GamePhotoManager extends PhotoManager {
	
	/**
	 *
	 */
	protected static final PhotoManager instance = new GamePhotoManager();
	
	public static PhotoManager getInstance() {
		return instance;
	}
	

	private static final Logger log = Logger.getLogger(GamePhotoManager.class.getName());
	
	public GamePhotoManager() {
		photoTagCollector = GamePhotoFactory.getInstance().createPhotoTagCollector();
	}

	@Override
	public void loadPhotos() {
		Collection<GamePhoto> existingPhotos = ObjectifyService.run(new Work<Collection<GamePhoto>>() {
			@Override
			public Collection<GamePhoto> run() {
				Collection<GamePhoto> existingPhotos = new ArrayList<GamePhoto>();
				readObjects(existingPhotos, GamePhoto.class);
				return existingPhotos;
			}
		});

		for (GamePhoto photo : existingPhotos) {
			if (!doHasPhoto(photo.getId())) {
				log.config(LogBuilder.createSystemMessage().
						addParameter("Load Photo with ID", photo.getIdAsString()).toString());
				loadScaledImages(photo);
				doAddPhoto(photo);
			} else {
				log.config(LogBuilder.createSystemMessage().
						addParameter("Already loaded Photo", photo.getIdAsString()).toString());
			}
		}

		log.info(LogBuilder.createSystemMessage().addMessage("All photos loaded.").toString());
	}

	@Override
	public void savePhoto(Photo photo) {
		super.savePhoto(photo);
	}

	@Override
	public void savePhotos() throws IOException {
		super.savePhotos();
	}

	@Override
	public Photo createPhoto(String filename, Image uploadedImage) throws Exception {
		return super.createPhoto(filename, uploadedImage);
	}

	@Override
	public void addPhoto(Photo photo) throws IOException {
		// TODO Auto-generated method stub
		super.addPhoto(photo);
	}	

}
