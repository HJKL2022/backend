package louie.hanse.shareplate.web.dto.chatroom;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import louie.hanse.shareplate.domain.Chat;
import louie.hanse.shareplate.domain.ChatRoom;
import louie.hanse.shareplate.domain.Entry;
import louie.hanse.shareplate.domain.Member;

@Getter
public class ChatRoomListResponse {

    private Long id;
    private String shareThumbnailImageUrl;
    private int currentRecruitment;
    private boolean cancel;
    private String recentMessage;
    private LocalDateTime recentMessageDataTime;
    private List<String> recruitmentMemberNicknames;
    private List<String> recruitmentMemberImageUrls;
    private int unreadCount;

    public ChatRoomListResponse(ChatRoom chatRoom, Chat chat, int unreadCount, Long memberId) {
        this.id = chatRoom.getId();
        this.shareThumbnailImageUrl = chatRoom.getShare().getShareImages().get(0).getImageUrl();
        this.currentRecruitment = chatRoom.getShare().getCurrentRecruitment();
        this.cancel = chatRoom.getShare().isCancel();
        this.recentMessage = chat.getContents();
        this.recentMessageDataTime = chat.getWrittenDateTime();
        this.recruitmentMemberNicknames = chatRoom.getShare().getEntries().stream()
            .map(Entry::getMember).filter(member -> !member.getId().equals(memberId))
            .map(Member::getNickname).collect(Collectors.toList());
        this.recruitmentMemberImageUrls = chatRoom.getShare().getEntries().stream()
            .map(Entry::getMember).filter(member -> !member.getId().equals(memberId))
            .map(Member::getThumbnailImageUrl).collect(Collectors.toList());
        this.unreadCount = unreadCount;
    }

    public ChatRoomListResponse(ChatRoom chatRoom, int unreadCount, Long memberId) {
        this.id = chatRoom.getId();
        this.shareThumbnailImageUrl = chatRoom.getShare().getShareImages().get(0).getImageUrl();
        this.currentRecruitment = chatRoom.getShare().getCurrentRecruitment();
        this.cancel = chatRoom.getShare().isCancel();
        this.recruitmentMemberNicknames = chatRoom.getShare().getEntries().stream()
            .map(Entry::getMember).filter(member -> !member.getId().equals(memberId))
            .map(Member::getNickname).collect(Collectors.toList());
        this.recruitmentMemberImageUrls = chatRoom.getShare().getEntries().stream()
            .map(Entry::getMember).filter(member -> !member.getId().equals(memberId))
            .map(Member::getThumbnailImageUrl).collect(Collectors.toList());
        this.unreadCount = unreadCount;
    }
}
