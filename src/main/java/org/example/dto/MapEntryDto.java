package org.example.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public record MapEntryDto(
        String mapName,
        int distance
) {
    public static List<MapEntryDto> from(Map<String,Integer> map) {
        List<MapEntryDto> mapList = new ArrayList<>();
        Set<String> keys = map.keySet();
        for(String key: keys) {
            mapList.add(new MapEntryDto(key, map.get(key)));
        }
        return mapList;
    }
}
