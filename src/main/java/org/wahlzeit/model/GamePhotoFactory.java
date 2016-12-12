package org.wahlzeit.model;

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

public class GamePhotoFactory extends PhotoFactory {
	
	private static final Logger log = Logger.getLogger(GamePhotoFactory.class.getName());
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static GamePhotoFactory instance = null;
	
	public GamePhotoFactory() {
	}
	
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}

	/**
	 * Public singleton access method.
	 */
	public static synchronized GamePhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic PhotoFactory").toString());
			setInstance(new GamePhotoFactory());
		}

		return instance;
	}
	
	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(GamePhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PhotoFactory twice");
		}

		instance = photoFactory;
	}
	
	/**
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto() {
		return new GamePhoto();
	}

	/**
	 * Creates a new photo with the specified id
	 * @throws PhotoComponentException 
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto(PhotoId id) throws PhotoComponentException {
		return new GamePhoto(id);
	}

	@Override
	public Photo loadPhoto(PhotoId id) {
		return null;
	}
	
	

}
