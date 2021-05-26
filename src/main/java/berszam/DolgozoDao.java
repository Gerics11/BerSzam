package berszam;

import berszam.model.Dolgozo;
import jpa.GenericJpaDao;

import javax.persistence.Persistence;


public class DolgozoDao extends GenericJpaDao<Dolgozo> {

    private static DolgozoDao instance;

    public DolgozoDao() {
        super(Dolgozo.class);
    }

    public static DolgozoDao getInstance() {
        if (instance == null) {
            instance = new DolgozoDao();
            instance.setEntityManager(
                    Persistence.createEntityManagerFactory("berszamdb")
                            .createEntityManager());
        }
        return instance;
    }


}
