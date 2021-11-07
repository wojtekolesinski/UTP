package zad1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {
	private Map<String, List<String>> langsMap;
	private Map<String, List<String>> progsMap;	
	
	public ProgLang(String path) {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			langsMap = new LinkedHashMap<String, List<String>>();
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split("\\t");
				List<String> progs = new ArrayList<String>();
				
				for (int i = 1; i < data.length; i++) {
					if (!progs.contains(data[i])) {
						progs.add(data[i]);
					}
				}
				
				langsMap.put(data[0], progs);				
			}
			
			
			progsMap = new LinkedHashMap<String, List<String>>();
			for (Entry<String, List<String>> entry: langsMap.entrySet()) {
				for (String programmer : entry.getValue()) {
					if (!progsMap.containsKey(programmer)) {
						progsMap.put(programmer, new ArrayList<>());
					}
					progsMap.get(programmer).add(entry.getKey());					
				}
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, List<String>> getLangsMap() {
		return langsMap;
	}

	public Map<String, List<String>> getProgsMap() {
		return progsMap;
	}
	
	public Map<String, List<String>> getLangsMapSortedByNumOfProgs() {
		return sorted(langsMap, (e1, e2) -> {
			if (e1.getValue().size() != e2.getValue().size())
				return e2.getValue().size() - e1.getValue().size();
			return e1.getKey().compareTo(e2.getKey());
			});
	}

	public Map<String, List<String>> getProgsMapSortedByNumOfLangs() {
		return sorted(progsMap, (e1, e2) -> {
			if (e1.getValue().size() != e2.getValue().size())
				return e2.getValue().size() - e1.getValue().size();
			return e1.getKey().compareTo(e2.getKey());
			});
	}
	
	public Map<String, List<String>> getProgsMapForNumOfLangsGreaterThan(int n) {
		return filtered(progsMap, e -> e.getValue().size() > n);
	}
	
	public <K,V> Map<K, V> sorted(Map<K, V> map, Comparator<Entry<K, V>> comparator) {
		return map.entrySet()
					.stream()
					.sorted(comparator)
					.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (oldVal, newVal) -> newVal, LinkedHashMap::new));
	}
	
	public <K, V> Map<K, V> filtered(Map<K, V> map, Predicate<Entry<K, V>> predicate) {
		return map.entrySet()
				.stream()
				.filter(predicate)
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (oldVal, newVal) -> newVal, LinkedHashMap::new));		
	}
}
