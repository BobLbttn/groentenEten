package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.repositories.FiliaalRepository;
import be.vdab.valueobjects.PostcodeReeks;

@Service
@ReadOnlyTransactionService
public class DefaultFiliaalService implements FiliaalService {
	private final FiliaalRepository filiaalRepository;
	
	DefaultFiliaalService(FiliaalRepository filiaalRepository) { 
		this.filiaalRepository = filiaalRepository;
	}
	
	@Override
	@ModifyingTransactionalServiceMethod
	public void create(Filiaal filiaal) {
		filiaalRepository.save(filiaal);
	}

	@Override
	public Optional<Filiaal> read(long id) {
		return Optional.ofNullable(filiaalRepository.findOne(id));
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void update(Filiaal filiaal) {
		filiaalRepository.save(filiaal);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void delete(long id) {
		Optional<Filiaal> optionalFiliaal = read(id);
		if (optionalFiliaal.isPresent()) {
			if ( ! optionalFiliaal.get().getWerknemers().isEmpty()) {
				throw new FiliaalHeeftNogWerknemersException();
			}
			filiaalRepository.delete(id);
		}	
	}

	@Override
	public List<Filiaal> findAll() {
		return filiaalRepository.findAll(new Sort("naam"));	
	}

	@Override
	public long findAantalFilialen() {
		return filiaalRepository.count();
	}
	
	@Override
	public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
		return filiaalRepository.findByAdresPostcodeBetweenOrderByNaam(
													reeks.getVanpostcode(),
													reeks.getTotpostcode());
	}
	
	@Override
	public List<Filiaal> findNietAfgeschreven() {
		return filiaalRepository.findByWaardeGebouwNot(BigDecimal.ZERO);
	}
	
	@Override
	@ModifyingTransactionalServiceMethod
	public void afschrijven(Filiaal filiaal) {
		filiaal.afschrijven(); 
	}
}
