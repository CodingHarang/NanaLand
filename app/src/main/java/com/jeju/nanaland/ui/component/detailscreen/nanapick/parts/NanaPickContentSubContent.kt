package com.jeju.nanaland.ui.component.detailscreen.nanapick.parts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.nanapick.NanaPickSubContentAdditionalInfoData
import com.jeju.nanaland.ui.component.detailscreen.nanapick.NanaPickContentAttractivePoint
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentDescription
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentImage
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentNumber
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentSubTitle
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentTag
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentTitle
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentAdditionalInfo

@Composable
fun NanaPickContentSubContent(
    index: Int,
    subTitle: String?,
    title: String?,
    imageUri: String?,
    content: String?,
    additionalInfoList: List<NanaPickSubContentAdditionalInfoData>,
    tagList: List<String?>
) {
    Column {
        NanaPickContentSubContentSubTitle(text = subTitle)

        Spacer(Modifier.height(4.dp))

        Row {
            NanaPickContentSubContentNumber(index = index)

            Spacer(Modifier.width(8.dp))

            NanaPickContentSubContentTitle(text = title)
        }

        Spacer(Modifier.height(20.dp))

        NanaPickContentSubContentImage(imageUri = imageUri)

        Spacer(Modifier.height(16.dp))

        NanaPickContentSubContentDescription(text = content)

        Spacer(Modifier.height(24.dp))

        additionalInfoList.forEach { info ->
            if (info.infoEmoji != "SPECIAL") {
                NanaPickContentSubContentAdditionalInfo(
                    infoEmoji = info.infoEmoji,
                    infoKey = info.infoKey,
                    infoValue = info.infoValue
                )

                Spacer(Modifier.height(8.dp))
            }
        }

        Spacer(Modifier.height(8.dp))
        
        Row {
            tagList.forEach { tag ->
                NanaPickContentSubContentTag(text = tag)

                Spacer(Modifier.width(8.dp))
            }
        }

        Spacer(Modifier.height(16.dp))

//        if (additionalInfoList.map { it.infoEmoji }.contains("SPECIAL")) {
            NanaPickContentAttractivePoint()
//        }
    }
}