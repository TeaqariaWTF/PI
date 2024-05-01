package dev.sanmer.pi.ui.screens.sessions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.sanmer.pi.R
import dev.sanmer.pi.model.ISessionInfo
import dev.sanmer.pi.ui.component.LabelItem

@Composable
internal fun SessionItem(
    session: ISessionInfo,
    onClick: () -> Unit
) = Row(
    modifier = Modifier
        .clip(RoundedCornerShape(15.dp))
        .clickable(
            enabled = true,
            onClick = onClick,
        )
        .padding(all = 12.dp)
        .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(12.dp)
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        AppIconItem(data = session.installer)

        Icon(
            painter = painterResource(id = R.drawable.arrow_narrow_down),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )

        AppIconItem(data = session.appIcon)
    }

    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = session.installerLabel.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = session.installerPackageName.toString(),
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = session.appLabel.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = session.appPackageName.toString(),
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(6.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            LabelItem(text = stringResource(id = R.string.sessions_user, session.userId))
            LabelItem(text = stringResource(id = R.string.sessions_id, session.sessionId))
            if (session.isActive) LabelItem(text = stringResource(id = R.string.sessions_active))
            if (session.isStaged) LabelItem(text = stringResource(id = R.string.sessions_staged))
            if (session.isCommitted) LabelItem(text = stringResource(id = R.string.sessions_committed))
        }
    }
}

@Composable
private fun AppIconItem(
    data: Any?
) {
    val context = LocalContext.current
    AsyncImage(
        modifier = Modifier.size(35.dp),
        model = ImageRequest.Builder(context)
            .data(data)
            .fallback(android.R.drawable.sym_def_app_icon)
            .crossfade(true)
            .build(),
        contentDescription = null
    )
}