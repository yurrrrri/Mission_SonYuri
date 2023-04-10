package com.ll.gramgram.boundedContext.likeablePerson.service;

import com.ll.gramgram.base.rsData.RsData;
import com.ll.gramgram.boundedContext.instaMember.entity.InstaMember;
import com.ll.gramgram.boundedContext.instaMember.service.InstaMemberService;
import com.ll.gramgram.boundedContext.likeablePerson.entity.LikeablePerson;
import com.ll.gramgram.boundedContext.likeablePerson.repository.LikeablePersonRepository;
import com.ll.gramgram.boundedContext.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeablePersonService {
    private final LikeablePersonRepository likeablePersonRepository;
    private final InstaMemberService instaMemberService;

    @Transactional
    public RsData<LikeablePerson> like(Member member, String username, int attractiveTypeCode) {
        if ( member.hasConnectedInstaMember() == false ) {
            return RsData.of("F-2", "먼저 본인의 인스타그램 아이디를 입력해야 합니다.");
        }

        if (member.getInstaMember().getUsername().equals(username)) {
            return RsData.of("F-1", "본인을 호감상대로 등록할 수 없습니다.");
        }

        List<LikeablePerson> likeablePeople = member.getInstaMember().getFromLikeablePeople();

        if(likeablePeople.stream().anyMatch(likeablePerson -> likeablePerson.getToInstaMember().getUsername().equals(username))){
            LikeablePerson likeablePerson = likeablePeople.stream().filter(likeablePerson1 -> likeablePerson1.getToInstaMember().getUsername().equals(username)).findFirst().get();
            if(likeablePerson.getAttractiveTypeCode() == attractiveTypeCode) {
                return RsData.of("F-4", "호감상대를 중복으로 등록할 수 없습니다.");
            } else {
                return update(member, likeablePerson, attractiveTypeCode);
            }
        }

        if(likeablePeople.size() >= 10) {
            return RsData.of("F-3", "호감상대는 최대 10명까지 등록할 수 있습니다.");
        }

        InstaMember fromInstaMember = member.getInstaMember();
        InstaMember toInstaMember = instaMemberService.findByUsernameOrCreate(username).getData();

        LikeablePerson likeablePerson = LikeablePerson
                .builder()
                .fromInstaMember(fromInstaMember) // 호감을 표시하는 사람의 인스타 멤버
                .fromInstaMemberUsername(member.getInstaMember().getUsername()) // 중요하지 않음
                .toInstaMember(toInstaMember) // 호감을 받는 사람의 인스타 멤버
                .toInstaMemberUsername(toInstaMember.getUsername()) // 중요하지 않음
                .attractiveTypeCode(attractiveTypeCode) // 1=외모, 2=능력, 3=성격
                .build();

        likeablePersonRepository.save(likeablePerson); // 저장
        fromInstaMember.addFromLikeablePerson(likeablePerson);
        toInstaMember.addToLikeablePerson(likeablePerson);

        return RsData.of("S-1", "입력하신 인스타 유저(%s)가 호감상대로 등록되었습니다.".formatted(username), likeablePerson);
    }

    public List<LikeablePerson> findByFromInstaMemberId(Long fromInstaMemberId) {
        return likeablePersonRepository.findByFromInstaMemberId(fromInstaMemberId);
    }

    public LikeablePerson findById(Long id) {
        return likeablePersonRepository.findById(id).orElse(null);
    }

    @Transactional
    public RsData<LikeablePerson> delete(Member member, LikeablePerson likeablePerson){
        if(likeablePerson == null) {
            return RsData.of("F-1", "삭제할 대상이 없습니다.");
        }
        if(!member.getInstaMember().getId().equals(likeablePerson.getFromInstaMember().getId())){
            return RsData.of("F-2", "삭제 권한이 없습니다.");
        }
        likeablePersonRepository.delete(likeablePerson);

        String deleteUsername = likeablePerson.getToInstaMember().getUsername();
        return RsData.of("S-1", "호감상대 %s 님이 삭제되었습니다.".formatted(deleteUsername));
    }

    @Transactional
    public RsData<LikeablePerson> update(Member member, LikeablePerson likeablePerson, int attractiveTypeCode) {
        if ( member.hasConnectedInstaMember() == false ) {
            return RsData.of("F-1", "먼저 본인의 인스타그램 아이디를 입력해야 합니다.");
        }

        LikeablePerson likeablePerson1 = likeablePerson.toBuilder().attractiveTypeCode(attractiveTypeCode).build();
        likeablePersonRepository.save(likeablePerson1);
        return RsData.of("S-2", "%s에 대한 호감 사유를 변경하였습니다.".formatted(likeablePerson.getToInstaMember().getUsername()));
    }
}
