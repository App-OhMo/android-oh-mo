package oh.mo.presentation.posting

import androidx.lifecycle.ViewModel

class PostingViewModel: ViewModel() {
    val chipOfTop = arrayListOf("티셔츠", "블라우스", "셔츠", "니트/스웨터", "후드", "맨투맨", "원피스", "민소매")
    val chipOfBottom = arrayListOf("반바지", "슬랙스", "청바지", "스커트", "원피스", "레깅스", "린넨바지", "트레이닝바지")
    val chipOfOuter = arrayListOf("가디건", "자켓", "후드집업", "코트", "점퍼/야상", "조끼", "롱패딩", "숏패딩")
    val chipOfShoe = arrayListOf("운동화", "구두", "단화", "부츠", "슬리퍼", "샌들")
    val chipOfEtc = arrayListOf("모자", "목도리", "선글라스", "벨트", "장갑", "우산")
}