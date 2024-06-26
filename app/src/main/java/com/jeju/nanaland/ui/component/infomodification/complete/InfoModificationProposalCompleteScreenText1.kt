package com.jeju.nanaland.ui.component.infomodification.complete

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02
import com.jeju.nanaland.util.resource.getString

@Composable
fun InfoModificationProposalCompleteScreenText1() {
    Text(
        text = getString(R.string.info_modification_proposal_heading4),
        color = getColor().main,
        style = largeTitle02,
        textAlign = TextAlign.Center
    )
}