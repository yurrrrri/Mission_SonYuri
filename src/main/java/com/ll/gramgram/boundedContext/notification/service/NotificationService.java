package com.ll.gramgram.boundedContext.notification.service;

import com.ll.gramgram.base.rsData.RsData;
import com.ll.gramgram.boundedContext.instaMember.entity.InstaMember;
import com.ll.gramgram.boundedContext.likeablePerson.entity.LikeablePerson;
import com.ll.gramgram.boundedContext.notification.entity.Notification;
import com.ll.gramgram.boundedContext.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<Notification> findByToInstaMember(InstaMember toInstaMember) {
        return notificationRepository.findByToInstaMember(toInstaMember);
    }

    public RsData<Notification> createAfterLike(LikeablePerson likeablePerson) {
        Notification notification = create(likeablePerson, "Like", 0, likeablePerson.getAttractiveTypeCode());

        return RsData.of("S-1", "알림이 생성되었습니다.", notification);
    }

    public RsData<Notification> createAfterModify(LikeablePerson likeablePerson, int oldAttractiveTypeCode) {
        Notification notification = create(likeablePerson, "ModifyAttractiveType", oldAttractiveTypeCode, likeablePerson.getAttractiveTypeCode());

        return RsData.of("S-1", "알림이 생성되었습니다.", notification);
    }

    private Notification create(LikeablePerson likeablePerson, String typeCode, int oldAttractiveTypeCode, int newAttractiveTypeCode) {
        Notification notification = Notification
                .builder()
                .toInstaMember(likeablePerson.getToInstaMember())
                .fromInstaMember(likeablePerson.getFromInstaMember())
                .typeCode(typeCode)
                .oldAttractiveTypeCode(oldAttractiveTypeCode)
                .newAttractiveTypeCode(newAttractiveTypeCode)
                .oldGender(null)
                .newGender(null)
                .build();

        notificationRepository.save(notification);

        return notification;
    }

    public void updateReadDate(List<Notification> notifications) {
        for(Notification notification : notifications) {
            if(notification == null) {
                notification.updateReadDate();
            }
        }
    }

    public boolean hasUnreadNotifications(InstaMember instaMember) {
        return notificationRepository.countByToInstaMemberAndReadDateIsNull(instaMember) > 0;
    }

}
