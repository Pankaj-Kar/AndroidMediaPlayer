package com.example.pankaj.mediaplayer;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class SongsManager {
	final String MEDIA_PATH = new String("/sdcard/");
	private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

	public SongsManager(){

	}

	public ArrayList<HashMap<String, String>> getPlayList(){
		System.out.println(MEDIA_PATH);
		if (MEDIA_PATH != null) {
			File home = new File(MEDIA_PATH);
			File[] listFiles = home.listFiles();
			System.out.println(home.listFiles());
			if (listFiles != null) {
				for (File file : listFiles) {
					System.out.println(file.getAbsolutePath());
					if (file.isDirectory()) {
						scanDirectory(file);
					} else {
						addSongToList(file);
					}
				}
			}
		}
		return songsList;
	}

	private void scanDirectory(File directory) {
		if (directory != null) {
			File[] listFiles = directory.listFiles();
			if (listFiles != null && listFiles.length > 0) {
				for (File file : listFiles) {
					if (file.isDirectory()) {
						scanDirectory(file);
					} else {
						addSongToList(file);
					}
				}
			}
		}
	}

	private void addSongToList(File song) {
		if (song.getName().endsWith(".mp3")) {
			HashMap<String, String> songMap = new HashMap<String, String>();
			songMap.put("songTitle",
					song.getName().substring(0, (song.getName().length() - 4)));
			songMap.put("songPath", song.getPath());
			songsList.add(songMap);
		}
	}


	class FileExtensionFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return (name.endsWith(".mp3") || name.endsWith(".MP3"));
		}
	}
}

