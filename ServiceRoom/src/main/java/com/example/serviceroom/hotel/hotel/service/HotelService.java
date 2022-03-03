package com.example.serviceroom.hotel.hotel.service;

import com.example.serviceroom.hotel.hotel.HotelBO;
import com.example.serviceroom.hotel.hotel.bean.HotelBean;
import com.example.serviceroom.hotel.hotel.hotelForm.HotelForm;
import com.example.serviceroom.hotel.hotel.repository.HotelRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class HotelService {
    private static final Logger log = LogManager.getLogger(HotelService.class);
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private EntityManager entityManager;



    public List<HotelBean> getListHotel(HotelForm form){
        StringBuilder strQuery = new StringBuilder("SELECT * ");
        strQuery.append("   FROM hotel h");
        strQuery.append("   JOIN area a ON a.guid = h.guid_area ");
        strQuery.append("   WHERE 1=1 ");
        if (Objects.nonNull(form.getGuidArea())){
            strQuery.append("   AND h.guid_area =  ").append(form.getGuidArea());
        }
        if (Objects.nonNull(form.getName())){
            strQuery.append("   AND h.name =  ").append(form.getName());
        }
        strQuery.append("   ORDER BY h.created_date ");
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery(strQuery.toString())
                .setResultTransformer(Transformers.aliasToBean(HotelBean.class));

        List lst = query.getResultList();
        return lst != null ? lst : new ArrayList<>();
    }


    public boolean createdHotel(HotelForm hotelForm) {
        try {
            ModelMapper modelMap = new ModelMapper();
            HotelBO hotelBO = modelMap.map(hotelForm, HotelBO.class);
            hotelBO.setGuid(UUID.randomUUID().toString());
            hotelRepository.save(hotelBO);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    public List<HotelBO> getAllHotel() {
            return hotelRepository.findAll();
    }


    public boolean deleteHotel(String guid) {
        try {
            HotelBO hotelBO = hotelRepository.findByGuid(guid).
                    orElseThrow(() -> new Exception("Hotel not found - " + guid));
            hotelRepository.delete(hotelBO);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

}
