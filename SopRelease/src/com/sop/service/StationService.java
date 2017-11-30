package com.sop.service;

import org.springframework.stereotype.Service;

import com.sop.util.NoteResult;

@Service
public interface StationService {
	public NoteResult findAllStations() throws Exception;
	public NoteResult findStation(String stationCode) throws Exception;
}
