package org.example.Helper;



import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;
import org.example.FilterDTO.EMPSDDTO;


import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class EmployeeIdConverterProvider implements ParamConverterProvider {


    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if(rawType.getName().equals(EMPSDDTO.class.getName())) {
            return new EmployeeIdParamConverter();
        }
        return null;
    }


    public class EmployeeIdParamConverter implements ParamConverter {

        @Override
        public Object fromString(String value) {

            EMPSDDTO empId = new EMPSDDTO();
            empId.setDeptcode(value.substring(0, 2));
            empId.setSeq(Integer.parseInt(value.substring(2, 6)));
            empId.setHireYear(Integer.parseInt(value.substring(6)));

            return empId;
        }

        @Override
        public String toString(Object value) {
            return value.toString();
        }
    }
}