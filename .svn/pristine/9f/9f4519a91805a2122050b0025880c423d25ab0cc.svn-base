package net.h2.web.core.config.initializer;

import org.dozer.CustomFieldMapper;
import org.dozer.classmap.ClassMap;
import org.dozer.fieldmap.FieldMap;
import org.hibernate.proxy.HibernateProxy;

public class DozerIntializer implements CustomFieldMapper {

	@Override
	public boolean mapField(Object source, Object destination, Object sourceFieldValue, ClassMap classMap,
			FieldMap fieldMapping) {

		if (sourceFieldValue instanceof HibernateProxy) {
			if (((HibernateProxy) sourceFieldValue).getHibernateLazyInitializer() != null) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
