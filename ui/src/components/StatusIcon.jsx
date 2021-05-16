import CheckCircleIcon from "@material-ui/icons/CheckCircle";
import StarsIcon from "@material-ui/icons/Stars";
import HighlightOffIcon from "@material-ui/icons/HighlightOff";

export default function StatusIcon(props) {
  const color = "primary";
  if (props.status === "NEW") {
    return <StarsIcon color={color} style={{ fontSize: props.fontSize }} />;
  } else if (props.status === "CANCELED") {
    return (
      <HighlightOffIcon color={color} style={{ fontSize: props.fontSize }} />
    );
  } else {
    return (
      <CheckCircleIcon color={color} style={{ fontSize: props.fontSize }} />
    );
  }
}
