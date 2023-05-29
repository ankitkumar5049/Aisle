package com.example.aisle.data

data class ResponseModel(
    val invites: Invites,
    val likes: Likes
)

data class Invites(
    val profiles: List<Profile>,
    val totalPages: Int,
    val pending_invitations_count: Int
)

data class Profile(
    val general_information: GeneralInformation,
    val approved_time: Double,
    val disapproved_time: Double,
    val photos: List<Photo>,
    val user_interests: List<Any>,
    val work: Work,
    val preferences: List<Preference>,
    val instagram_images: Any?,
    val last_seen_window: String,
    val is_facebook_data_fetched: Boolean,
    val icebreakers: Any?,
    val story: Any?,
    val meetup: Any?,
    val verification_status: String,
    val has_active_subscription: Boolean,
    val show_concierge_badge: Boolean,
    val lat: Double,
    val lng: Double,
    val last_seen: Any?,
    val online_code: Int,
    val profile_data_list: List<ProfileData>
)

data class GeneralInformation(
    val date_of_birth: String,
    val date_of_birth_v1: String,
    val location: Location,
    val drinking_v1: Drinking,
    val first_name: String,
    val gender: String,
    val marital_status_v1: MaritalStatus,
    val ref_id: String,
    val smoking_v1: Smoking,
    val sun_sign_v1: SunSign,
    val mother_tongue: MotherTongue,
    val faith: Faith,
    val height: Int,
    val cast: Any?,
    val kid: Any?,
    val diet: Any?,
    val politics: Any?,
    val pet: Any?,
    val settle: Any?,
    val mbti: Any?,
    val age: Int
)

data class Location(
    val summary: String,
    val full: String
)

data class Drinking(
    val id: Int,
    val name: String,
    val name_alias: String
)

data class MaritalStatus(
    val id: Int,
    val name: String,
    val preference_only: Boolean
)

data class Smoking(
    val id: Int,
    val name: String,
    val name_alias: String
)

data class SunSign(
    val id: Int,
    val name: String
)

data class MotherTongue(
    val id: Int,
    val name: String
)

data class Faith(
    val id: Int,
    val name: String
)

data class Photo(
    val photo: String,
    val photo_id: Int,
    val selected: Boolean,
    val status: String?
)

data class Work(
    val industry_v1: Industry,
    val monthly_income_v1: Any?,
    val experience_v1: Experience,
    val highest_qualification_v1: HighestQualification,
    val field_of_study_v1: FieldOfStudy
)

data class Industry(
    val id: Int,
    val name: String,
    val preference_only: Boolean
)

data class Experience(
    val id: Int,
    val name: String,
    val name_alias: String
)

data class HighestQualification(
    val id: Int,
    val name: String,
    val preference_only: Boolean
)

data class FieldOfStudy(
    val id: Int,
    val name: String
)

data class Preference(
    val answer_id: Int,
    val id: Int,
    val value: Int,
    val preference_question: PreferenceQuestion
)

data class PreferenceQuestion(
    val first_choice: String,
    val second_choice: String
)

data class ProfileData(
    val question: String,
    val preferences: List<Preference>,
    val invitation_type: String
)

data class Likes(
    val profiles: List<LikeProfile>,
    val can_see_profile: Boolean,
    val likes_received_count: Int
)

data class LikeProfile(
    val first_name: String,
    val avatar: String
)
