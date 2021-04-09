import CheckCircleIcon from "@material-ui/icons/CheckCircle";
import StarsIcon from "@material-ui/icons/Stars";

export default function StatusIcon(props) {
  const color = "primary";
  if (props.status === "New") {
    return <StarsIcon color={color} style={{ fontSize: props.fontSize }} />;
  } else {
    return (
      <CheckCircleIcon color={color} style={{ fontSize: props.fontSize }} />
    );
  }
}
